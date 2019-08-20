package com.xbook.dao.product;

import com.xbook.entity.product.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anthonyzero
 * @since 2019-08-16
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 获取商品列表
     * @param productName
     * @param categoryIdList
     * @return
     */
    List<Product> selectProductSearch(@Param("productName") String productName, @Param("categoryIdList") List<Integer> categoryIdList);
}
