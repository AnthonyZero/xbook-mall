package com.xbook.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.enums.*;
import com.xbook.common.utils.CalcUtil;
import com.xbook.dao.cart.CartMapper;
import com.xbook.dao.order.OrderItemMapper;
import com.xbook.dao.order.OrderMapper;
import com.xbook.dao.product.ProductMapper;
import com.xbook.dao.user.ShippingMapper;
import com.xbook.entity.cart.Cart;
import com.xbook.entity.order.*;
import com.xbook.entity.product.Product;
import com.xbook.entity.user.Shipping;
import com.xbook.entity.user.ShippingVo;
import com.xbook.order.OrderService;
import com.xbook.order.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service(version = SysConstant.XBOOK_MALL_ORDER_VERSION)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    @Transactional
    public OrderVo createOrder(Integer userId, Integer shippingId) {
        //获取购物车选择的商品
        List<Cart> cartList = cartMapper.selectList(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId).eq(Cart::getChecked, CartCheckEnum.CHECKED.getCode()));

        List<OrderItem> orderItemList = this.getCartOrderItem(userId, cartList);
        BigDecimal payment = getOrderTotalPrice(orderItemList);

        Order mainOrder = this.createMainOrder(userId, shippingId, payment);//创建订单
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderNo(mainOrder.getOrderNo());
            orderItem.setCreateTime(mainOrder.getCreateTime());
            orderItemMapper.insert(orderItem); //插入订单子明细
        }
        this.reduceProductStock(orderItemList); //减库存
        for (Cart cart : cartList) {
            cartMapper.deleteById(cart.getId()); //删除购物车中商品
        }

        OrderVo orderVo = constructOrderVo(mainOrder, orderItemList);
        return orderVo;
    }

    @Override
    public OrderProductVo getOrderCartProduct(Integer userId) {
        OrderProductVo orderProductVo = new OrderProductVo();

        //获取购物车选择的商品
        List<Cart> cartList = cartMapper.selectList(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId).eq(Cart::getChecked, CartCheckEnum.CHECKED.getCode()));
        List<OrderItem> orderItemList = this.getCartOrderItem(userId, cartList);
        List<OrderItemVo> orderItemVoList = Lists.newArrayList();
        BigDecimal payment = new BigDecimal("0");
        for(OrderItem orderItem : orderItemList){
            payment = CalcUtil.addBig(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
            orderItemVoList.add(this.constructOrderItemVo(orderItem));
        }

        orderProductVo.setProductTotalPrice(payment); //需支付的总价
        orderProductVo.setOrderItemVoList(orderItemVoList); //订单商品数据
        orderProductVo.setImageHost(SysConstant.IMG_HOST);
        return orderProductVo;
    }

    @Override
    public PageInfo pageUserOrder(Integer userId, int pageNum, int pageSize) {
        if (userId == null) {
            throw new OrderException(CodeMsgEnum.SESSION_ERROR);
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderMapper.selectList(new LambdaQueryWrapper<Order>().eq(Order::getUserId, userId)); //分页
        List<OrderVo> orderVoList = Lists.newArrayList();
        orderList.stream().forEach(order -> {
            List<OrderItem>  orderItemList = orderItemMapper.selectList(new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderNo, order.getOrderNo()));
            OrderVo orderVo = constructOrderVo(order, orderItemList);
            orderVoList.add(orderVo);
        });
        PageInfo pageInfo = new PageInfo(orderList);
        pageInfo.setList(orderVoList); //真正需要返回的数据
        return pageInfo;
    }


    /**
     * 获取购物车中选中商品（需验证产品状态和数量）
     *
     * @param userId
     * @param cartList
     * @return
     */
    private List<OrderItem> getCartOrderItem(Integer userId, List<Cart> cartList) {
        List<OrderItem> orderItemList = Lists.newArrayList();
        if (cartList == null || cartList.size() == 0) {
            throw new OrderException(CodeMsgEnum.CART_PRODUCT_NOT_EXIST);
        }
        //校验购物车的数据,包括产品的状态和数量
        cartList.stream().forEach(cart -> {
            OrderItem orderItem = new OrderItem();
            Product product = productMapper.selectById(cart.getProductId());
            if (ProductStatusEnum.ON.getCode() != product.getStatus()) {
                throw new OrderException(CodeMsgEnum.PRODUCT_LOWER_SHELF);
            }
            if (cart.getQuantity() > product.getStock()) {
                throw new OrderException(CodeMsgEnum.PRODUCT_STOCK_ERROR.fillArgs(product.getName()));
            }
            orderItem.setUserId(userId);
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setProductImage(product.getMainImage());
            orderItem.setCurrentUnitPrice(product.getPrice());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setTotalPrice(CalcUtil.multiplyBig(product.getPrice().doubleValue(), cart.getQuantity()));
            orderItemList.add(orderItem);
        });
        return orderItemList;
    }


    /**
     * 计算购买商品的总价
     *
     * @param orderItemList
     * @return
     */
    private BigDecimal getOrderTotalPrice(List<OrderItem> orderItemList) {
        BigDecimal payment = new BigDecimal("0");
        for (OrderItem orderItem : orderItemList) {
            payment = CalcUtil.addBig(payment.doubleValue(), orderItem.getTotalPrice().doubleValue());
        }
        return payment;
    }


    private Order createMainOrder(Integer userId, Integer shippingId, BigDecimal payment) {
        Order order = new Order();
        long orderNo = generateOrderId();
        order.setOrderNo(orderNo);
        order.setStatus(OrderStatusEnum.NO_PAY.getCode());
        order.setPostage(0);
        order.setPaymentType(PaymentTypeEnum.ONLINE_PAY.getCode());
        order.setPayment(payment);

        order.setUserId(userId);
        order.setShippingId(shippingId);
        order.setCreateTime(LocalDateTime.now());
        //发货时间等等
        //付款时间等等
        orderMapper.insert(order);
        return order;
    }

    /**
     * 生成订单ID
     *
     * @return
     */
    private long generateOrderId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end);
        long id = new Long(str);
        return id;
    }

    /**
     * 减去商品库存
     *
     * @param orderItemList
     */
    private void reduceProductStock(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            Product product = productMapper.selectById(orderItem.getProductId());
            product.setStock(product.getStock() - orderItem.getQuantity());
            productMapper.updateById(product);
        }
    }

    /**
     * 获取订单详情
     * @param order
     * @param orderItemList
     * @return
     */
    private OrderVo constructOrderVo(Order order, List<OrderItem> orderItemList) {
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(order.getOrderNo());
        orderVo.setPayment(order.getPayment());
        orderVo.setPaymentType(order.getPaymentType());
        orderVo.setPaymentTypeDesc(PaymentTypeEnum.codeOf(order.getPaymentType()).getMsg());

        orderVo.setPostage(order.getPostage());
        orderVo.setStatus(order.getStatus());
        orderVo.setStatusDesc(OrderStatusEnum.codeOf(order.getStatus()).getMsg());

        orderVo.setShippingId(order.getShippingId());
        Shipping shipping = shippingMapper.selectById(order.getShippingId());
        if (shipping != null) {
            orderVo.setReceiverName(shipping.getReceiverName());
            ShippingVo shippingVo = new ShippingVo(); //收货地址
            shippingVo.setReceiverName(shipping.getReceiverName());
            shippingVo.setReceiverAddress(shipping.getReceiverAddress());
            shippingVo.setReceiverProvince(shipping.getReceiverProvince());
            shippingVo.setReceiverCity(shipping.getReceiverCity());
            shippingVo.setReceiverDistrict(shipping.getReceiverDistrict());
            shippingVo.setReceiverMobile(shipping.getReceiverMobile());
            shippingVo.setReceiverZip(shipping.getReceiverZip());
            shippingVo.setReceiverPhone(shippingVo.getReceiverPhone());
            orderVo.setShippingVo(shippingVo);
        }

        orderVo.setPaymentTime(Optional.ofNullable(order.getPaymentTime()).map(u -> u.format(DateTimeFormatter.ofPattern(SysConstant.DATE_TIME_PATTERN))).orElse(""));
        orderVo.setSendTime(Optional.ofNullable(order.getSendTime()).map(u -> u.format(DateTimeFormatter.ofPattern(SysConstant.DATE_TIME_PATTERN))).orElse(""));
        orderVo.setEndTime(Optional.ofNullable(order.getEndTime()).map(u -> u.format(DateTimeFormatter.ofPattern(SysConstant.DATE_TIME_PATTERN))).orElse(""));
        orderVo.setCreateTime(Optional.ofNullable(order.getCreateTime()).map(u -> u.format(DateTimeFormatter.ofPattern(SysConstant.DATE_TIME_PATTERN))).orElse(""));
        orderVo.setCloseTime(Optional.ofNullable(order.getCloseTime()).map(u -> u.format(DateTimeFormatter.ofPattern(SysConstant.DATE_TIME_PATTERN))).orElse(""));
        orderVo.setImageHost(SysConstant.IMG_HOST);

        //子订单明细
        List<OrderItemVo> orderItemVoList = Lists.newArrayList();
        for (OrderItem orderItem : orderItemList) {
            OrderItemVo orderItemVo = constructOrderItemVo(orderItem);
            orderItemVoList.add(orderItemVo);
        }
        orderVo.setOrderItemVoList(orderItemVoList);
        return orderVo;
    }

    /**
     * 获取订单商品详情
     * @param orderItem
     * @return
     */
    private OrderItemVo constructOrderItemVo(OrderItem orderItem) {
        OrderItemVo orderItemVo = new OrderItemVo();
        orderItemVo.setOrderNo(orderItem.getOrderNo());
        orderItemVo.setProductId(orderItem.getProductId());
        orderItemVo.setProductName(orderItem.getProductName());
        orderItemVo.setProductImage(orderItem.getProductImage());
        orderItemVo.setCurrentUnitPrice(orderItem.getCurrentUnitPrice());
        orderItemVo.setQuantity(orderItem.getQuantity());
        orderItemVo.setTotalPrice(orderItem.getTotalPrice());

        orderItemVo.setCreateTime(Optional.ofNullable(orderItem.getCreateTime()).map(u -> u.format(DateTimeFormatter.ofPattern(SysConstant.DATE_TIME_PATTERN))).orElse(""));
        return orderItemVo;
    }
}
