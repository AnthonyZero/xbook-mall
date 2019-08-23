package com.xbook.entity.product;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品列表数据
 */
@Data
public class ProductData implements Serializable {

    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private BigDecimal price;

    private Integer status;

    private String imageHost;
}
