package com.xbook.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.core.Result;
import com.xbook.entity.user.Shipping;
import com.xbook.user.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 收获地址服务
 */
@RestController
@RequestMapping("/shipping")
@Slf4j
public class ShippingController extends BaseController{

    @Reference(version = SysConstant.XBOOK_MALL_USER_VERSION, retries = 0, timeout = 1800000)
    private AddressService addressService;


    /**
     * 添加地址
     */
    @RequestMapping("/add")
    public Result add(HttpServletRequest httpServletRequest, Shipping shipping){
        Integer userId = getCurrentUserId(httpServletRequest);
        Map map = addressService.saveAddress(shipping, userId);
        return Result.success(map);
    }

    /**
     * 删除地址
     */
    @RequestMapping("/del")
    public Result del(HttpServletRequest httpServletRequest, Integer shippingId){
        Integer userId = getCurrentUserId(httpServletRequest);
        addressService.removeAddress(shippingId, userId);
        return Result.success();
    }

    /**
     * 更新地址
     */
    @RequestMapping("/update")
    public Result update(HttpServletRequest httpServletRequest, Shipping shipping){
       addressService.updateAddress(shipping);
       return Result.success();
    }

    /**
     * 选择选中的地址
     */
    @RequestMapping("/select")
    public Result select(HttpServletRequest httpServletRequest, Integer shippingId){
        Integer userId = getCurrentUserId(httpServletRequest);
        Shipping address = addressService.getAddress(shippingId, userId);
        return Result.success(address);
    }

    /**
     * 地址列表
     */
    @RequestMapping("/list")
    public Result<PageInfo> list(HttpServletRequest httpServletRequest,
                                         @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Integer userId = getCurrentUserId(httpServletRequest);
        PageInfo pageInfo = addressService.pageAddress(userId, pageNum, pageSize);
        return Result.success(pageInfo);
    }
}
