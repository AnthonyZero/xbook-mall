package com.xbook.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xbook.cart.service.CartService;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.core.Result;
import com.xbook.common.enums.CartCheck;
import com.xbook.entity.cart.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController extends BaseController {

    @Reference(version = SysConstant.XBOOK_MALL_CART_VERSION)
    private CartService cartService;

    /**
     * 获取购物车商品购买数量
     * @param request
     * @return
     */
    @RequestMapping("/getCartProductCount")
    public Result getCartCount(HttpServletRequest request) {
        Integer Count = cartService.getCartCount(getCurrentUserId(request));
        return Result.success(Count);
    }

    /**
     * 添加到购物车
     * @param request
     * @return
     */
    @RequestMapping("/add")
    public Result addToCart(HttpServletRequest request, Integer productId, Integer count) {
        Integer currentUserId = getCurrentUserId(request);
        CartVo cartVo = cartService.addToCart(currentUserId, productId, count);
        return Result.success(cartVo);
    }

    /**
     * 获取购物车信息
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public Result getCartList(HttpServletRequest request) {
        Integer currentUserId = getCurrentUserId(request);
        CartVo cartVo = cartService.getUserCartInfo(currentUserId);
        return Result.success(cartVo);
    }


    /**
     * 购物车全选
     */
    @RequestMapping("/selectAll")
    public Result selectAll(HttpServletRequest request){
        Integer currentUserId = getCurrentUserId(request);

        return Result.success(cartService.selectOrUnSelect(currentUserId, CartCheck.CHECKED.getCode(),null));
    }

    /**
     * 购物车全不选
     */
    @RequestMapping("/unSelectAll")
    public Result unSelectAll(HttpServletRequest request){
        Integer currentUserId = getCurrentUserId(request);

        return Result.success(cartService.selectOrUnSelect(currentUserId, CartCheck.UN_CHECKED.getCode(),null));
    }

    /**
     * 购物车选中某个商品
     */
    @RequestMapping("/select")
    public Result select(HttpServletRequest request,Integer productId){
        Integer currentUserId = getCurrentUserId(request);

        return Result.success(cartService.selectOrUnSelect(currentUserId, CartCheck.CHECKED.getCode(), productId));
    }

    /**
     * 购物车取消选中某个商品
     */
    @RequestMapping("/unSelect")
    public Result unSelect(HttpServletRequest request,Integer productId){
        Integer currentUserId = getCurrentUserId(request);

        return Result.success(cartService.selectOrUnSelect(currentUserId, CartCheck.UN_CHECKED.getCode(), productId));
    }

    /**
     * 更新购物车某个产品数量
     * @param request
     * @param productId
     * @param count
     * @return
     */
    @RequestMapping("/update")
    public Result update(HttpServletRequest request,Integer productId, Integer count){
        Integer currentUserId = getCurrentUserId(request);
        CartVo cartVo = cartService.update(currentUserId, productId, count);
        return Result.success(cartVo);
    }


    /**
     * 移除购物车某个产品
     */
    @RequestMapping("/deleteProduct")
    public Result deleteProduct(HttpServletRequest request, String productIds){
        Integer currentUserId = getCurrentUserId(request);

        CartVo cartVo = cartService.delete(currentUserId, productIds);
        return Result.success(cartVo);
    }
}
