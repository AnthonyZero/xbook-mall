package com.xbook.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.core.Result;
import com.xbook.entity.order.OrderProductVo;
import com.xbook.entity.order.OrderVo;
import com.xbook.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController extends BaseController{

    @Reference(version = SysConstant.XBOOK_MALL_ORDER_VERSION)
    private OrderService orderService;

    /**
     * 创建订单
     */
    @RequestMapping("/create")
    public Result create(HttpServletRequest request, @RequestParam("shippingId") Integer shippingId){
        Integer userId = getCurrentUserId(request);
        OrderVo orderVo = orderService.createOrder(userId, shippingId);

        return Result.success(orderVo);
    }

    /**
     * 取消订单
     */
    @RequestMapping("/cancel")
    public Result cancel(HttpServletRequest request, @RequestParam("orderNo") Long orderNo){
        Integer userId = getCurrentUserId(request);
        orderService.cancelOrder(userId, orderNo);
        return Result.success();
    }

    /**
     * 获取订单商品信息
     */
    @RequestMapping("/getOrderCartProduct")
    public Result getOrderCartProduct(HttpServletRequest request){
        Integer userId = getCurrentUserId(request);
        OrderProductVo orderCartProduct = orderService.getOrderCartProduct(userId);
        return Result.success(orderCartProduct);
    }

    /**
     * 订单详情
     */
    @RequestMapping("/detail")
    public Result detail(HttpServletRequest request, @RequestParam("orderNo") Long orderNo){
        Integer userId = getCurrentUserId(request);
        OrderVo orderVo = orderService.getOrderDetail(userId, orderNo);
        return Result.success(orderVo);
    }

    /**
     * 订单列表
     */
    @RequestMapping("/list")
    public Result list(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Integer userId = getCurrentUserId(request);
        PageInfo pageInfo = orderService.pageUserOrder(userId, pageNum, pageSize);

        return Result.success(pageInfo);
    }
}
