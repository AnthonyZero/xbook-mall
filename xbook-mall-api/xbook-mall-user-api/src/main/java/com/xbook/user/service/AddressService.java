package com.xbook.user.service;

import com.github.pagehelper.PageInfo;
import com.xbook.entity.user.Shipping;

import java.util.Map;

public interface AddressService {

    /**
     * 新建地址
     * @param shipping
     * @param userId
     * @return
     */
    Map saveAddress(Shipping shipping, Integer userId);

    /**
     * 删除地址
     * @param shippingId
     * @param userId
     */
    void removeAddress(Integer shippingId, Integer userId);

    /**
     * 修改地址信息
     * @param shipping
     */
    void updateAddress(Shipping shipping);

    /**
     * 获取地址信息
     * @param shippingId
     * @param userId
     * @return
     */
    Shipping getAddress(Integer shippingId, Integer userId);

    /**
     * 分页获取用户地址
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo pageAddress(Integer userId, int pageNum, int pageSize);
}
