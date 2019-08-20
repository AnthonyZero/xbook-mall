package com.xbook.product.service;


import com.github.pagehelper.PageInfo;

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
     * 递归查询出所有品类
     * @param categoryId
     * @return
     */
    List<Integer> selectCategoryChildrenById(Integer categoryId);
}
