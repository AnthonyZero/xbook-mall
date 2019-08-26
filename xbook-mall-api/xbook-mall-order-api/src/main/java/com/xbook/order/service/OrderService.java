package com.xbook.order.service;

import com.github.pagehelper.PageInfo;
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

    /**
     * 获取用户订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo pageUserOrder(Integer userId, int pageNum, int pageSize);

    /**
     * 获取订单详情
     * @param userId
     * @param orderNo
     * @return
     */
    OrderVo getOrderDetail(Integer userId, Long orderNo);

    /**
     * 取消订单
     * @param userId
     * @param orderNo
     */
    void cancelOrder(Integer userId, Long orderNo);
}
