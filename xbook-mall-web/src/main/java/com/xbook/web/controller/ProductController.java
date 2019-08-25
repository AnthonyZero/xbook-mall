package com.xbook.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.core.Result;
import com.xbook.entity.product.ProductDetail;
import com.xbook.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Reference(version = SysConstant.XBOOK_MALL_PRODUCT_VERSION, retries = 0, timeout = 1800000)
    private ProductService productService;


    /**
     * 分页查询产品列表
     * @param keyword
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @RequestMapping("/list")
    public Result<PageInfo> list(@RequestParam(value = "keyword",required = false) String keyword,
                                 @RequestParam(value = "categoryId",required = false) Integer categoryId,
                                 @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                 @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        PageInfo pageInfo = productService.pageProduct(keyword,categoryId,orderBy,pageNum,pageSize);
        return Result.success(pageInfo);
    }


    /**
     * 商品详情
     * @param productId
     * @return
     */
    @RequestMapping("/detail")
    public Result<ProductDetail> detail(Integer productId){
        ProductDetail productDetail = productService.queryProductDetail(productId);
        return Result.success(productDetail);
    }
}
