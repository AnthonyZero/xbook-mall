package com.xbook.entity.product;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 */
@Data
public class ProductDetail {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private String createTime;
    private String updateTime;

    private String imageHost;
    private Integer parentCategoryId;
}
