package com.xbook.entity.cart;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车信息
 */
@Data
public class CartVo {
    private List<CartProductVo> cartProductVoList;
    private BigDecimal cartTotalPrice;
    private Boolean allChecked;//是否已经都勾选
    private String imageHost;
}
