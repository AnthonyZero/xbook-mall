package com.xbook.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.xbook.cart.service.CartService;
import com.xbook.cart.service.exception.CartException;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.enums.CartCheck;
import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.enums.ProductStatus;
import com.xbook.common.utils.CalcUtil;
import com.xbook.dao.cart.CartMapper;
import com.xbook.dao.product.ProductMapper;
import com.xbook.entity.cart.Cart;
import com.xbook.entity.cart.CartProductVo;
import com.xbook.entity.cart.CartVo;
import com.xbook.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service(version = SysConstant.XBOOK_MALL_CART_VERSION)
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Integer getCartCount(Integer userId) {
        if (userId == null) {
            return 0;
        }
        List<Cart> carts = cartMapper.selectList(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId));
        Integer sum = carts.stream().mapToInt(Cart::getQuantity).sum();
        return sum;
    }

    @Override
    @Transactional
    public CartVo addToCart(Integer userId, Integer productId, Integer count) {
        if (userId == null) {
            throw new CartException(CodeMsgEnum.SESSION_ERROR);
        }
        if (productId == null || count == null) {
            throw new CartException(CodeMsgEnum.PARAMETER_ERROR);
        }
        Product product = productMapper.selectById(productId);
        if (product == null) {
            throw new CartException(CodeMsgEnum.PRODUCT_NOT_EXIST);
        }
        if(ProductStatus.ON.getCode() != product.getStatus()){
            throw new CartException(CodeMsgEnum.PRODUCT_LOWER_SHELF);
        }
        //添加购物车记录 或者增加购买数量
        Cart cart = cartMapper.selectOne(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId).eq(Cart::getProductId, product));
        if (cart == null) {
            Cart cartItem = new Cart();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(count);
            cartItem.setChecked(CartCheck.CHECKED.getCode());
            cartItem.setCreateTime(LocalDateTime.now());
            cartMapper.insert(cartItem);
        } else {
            cart.setQuantity(cart.getQuantity() + count);
            cart.setUpdateTime(LocalDateTime.now());
            cartMapper.updateById(cart);
        }
        CartVo cartVo = structUserCartInfo(userId, true);
        return cartVo;
    }


    /**
     * 构造用户购物车信息
     * @param userId
     * @param isJudgeStock
     * @return
     */
    private CartVo structUserCartInfo(Integer userId, boolean isJudgeStock) {
        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = Lists.newArrayList();
        List<Cart> cartList = cartMapper.selectList(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId));
        BigDecimal cartTotalPrice = new BigDecimal("0");
        if(cartList != null && cartList.size() > 0) {
            for(Cart cart : cartList) {
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cart.getId());
                cartProductVo.setUserId(userId);
                cartProductVo.setProductId(cart.getProductId());

                Product product = productMapper.selectById(cart.getProductId());
                if (product != null) {
                    cartProductVo.setProductMainImage(product.getMainImage());
                    cartProductVo.setProductName(product.getName());
                    cartProductVo.setProductSubtitle(product.getSubtitle());
                    cartProductVo.setProductStatus(product.getStatus());
                    cartProductVo.setProductPrice(product.getPrice());
                    cartProductVo.setProductStock(product.getStock());
                    //判断库存
                    int buyLimitCount = 0; //实际买的数量
                    if (isJudgeStock) {
                        if (product.getStock() > cart.getQuantity()) {
                            //库存是够的
                            buyLimitCount = cart.getQuantity();
                            cartProductVo.setLimitQuantity(SysConstant.LIMIT_NUM_SUCCESS);
                        } else {
                            //库存不够,则返回当前最大库存 设置购买商品数量
                            buyLimitCount = product.getStock();
                            cartProductVo.setLimitQuantity(SysConstant.LIMIT_NUM_FAIL);
                            Cart cartItem = new Cart();
                            cartItem.setId(cart.getId());
                            cartItem.setQuantity(buyLimitCount);
                            cartMapper.updateById(cartItem);
                        }
                    } else {
                        buyLimitCount = cart.getQuantity();
                    }
                    cartProductVo.setQuantity(buyLimitCount);

                    cartProductVo.setProductTotalPrice(CalcUtil.multiplyBig(product.getPrice().doubleValue(), buyLimitCount));
                    cartProductVo.setProductChecked(cart.getChecked());
                }

                //选中的，就加入到总价中
                if(CartCheck.CHECKED.getCode() == cart.getChecked()){
                    cartTotalPrice = CalcUtil.addBig(cartTotalPrice.doubleValue(), cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVoList.add(cartProductVo);
            }
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));
        cartVo.setImageHost(SysConstant.IMG_HOST);
        return cartVo;
    }

    /**
     * 是否全部勾选
     * @param userId
     * @return
     */
    private Boolean getAllCheckedStatus(Integer userId) {
        if(userId == null) {
            return false;
        }
        return cartMapper.selectCount(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, userId).eq(Cart::getChecked, CartCheck.UN_CHECKED.getCode())) == 0;
    }
}
