package com.xbook.order;

import com.xbook.entity.order.OrderProductVo;
import com.xbook.entity.order.OrderVo;

public interface OrderService {

    /**
     * 创建订单
     * @param userId
     * @param shippingId
     * @return
     */
    OrderVo createOrder(Integer userId, Integer shippingId);

    /**
     * 获取订单商品信息
     * @param userId
     * @return
     */
    OrderProductVo getOrderCartProduct(Integer userId);
}
