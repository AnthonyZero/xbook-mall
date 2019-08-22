package com.xbook.cart.service;

import com.xbook.entity.cart.CartVo;

public interface CartService {

    /**
     * 获取在购物车里的产品购买数量
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

    /**
     * 获取购物车信息
     * @param userId
     * @return
     */
    CartVo getUserCartInfo(Integer userId);

    /**
     * 购物车选中/取消商品（单个商品或者全部商品 选中或取消选中）
     * @param userId
     * @param checked
     * @param productId
     */
    CartVo selectOrUnSelect(Integer userId, int checked, Integer productId);

    /**
     * 更新购物车产品购买数量
     * @param userId
     * @param productId
     * @param count
     * @return
     */
    CartVo update(Integer userId, Integer productId, Integer count);

    /**
     * 移除购物车中的商品
     * @param userId
     * @param productIds
     * @return
     */
    CartVo delete(Integer userId, String productIds);

    /**
     * 清空购物车
     * @param userId
     */
    void emptyCart(Integer userId);
}
