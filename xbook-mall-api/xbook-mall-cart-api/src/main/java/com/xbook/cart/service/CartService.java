package com.xbook.cart.service;

import com.xbook.entity.cart.CartVo;

public interface CartService {

    /**
     * 获取在购物车里的产品数量
     * @param userId
     * @return
     */
    Integer getCartCount(Integer userId);

    /**
     * 添加商品到购物车 返回购物车信息
     * @param userId
     * @param productId
     * @param count
     */
    CartVo addToCart(Integer userId, Integer productId, Integer count);
}
