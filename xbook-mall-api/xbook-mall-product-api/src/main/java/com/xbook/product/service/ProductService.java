package com.xbook.product.service;


import com.github.pagehelper.PageInfo;
import com.xbook.entity.product.ProductDetail;

import java.util.List;

public interface ProductService {

    /**
     * 分页查询商品列表
     * @param keyword
     * @param categoryId
     * @param orderBy
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo pageProduct(String keyword, Integer categoryId, String orderBy, int pageNum, int pageSize);


    /**
     * 获取商品详情
     * @param productId
     * @return
     */
    ProductDetail queryProductDetail(Integer productId);

    /**
     * 递归查询出所有分类
     * @param categoryId
     * @return
     */
    List<Integer> selectCategoryChildrenById(Integer categoryId);
}
