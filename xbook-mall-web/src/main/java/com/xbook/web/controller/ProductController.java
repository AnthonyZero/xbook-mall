package com.xbook.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xbook.common.constant.SysConstant;
import com.xbook.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Reference(version = SysConstant.XBOOK_MALL_PRODUCT_VERSION)
    private ProductService productService;
}
