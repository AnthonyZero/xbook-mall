package com.xbook.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.product.service.ProductService;
import com.xbook.product.service.exception.ProductException;

@Service(version = SysConstant.XBOOK_MALL_PRODUCT_VERSION)
public class ProductServiceImpl implements ProductService {


    @Override
    public void queryProduct(Integer productId) {
        throw new ProductException(CodeMsgEnum.PARAMETER_ERROR);
    }
}
