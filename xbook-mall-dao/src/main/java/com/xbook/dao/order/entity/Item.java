package com.xbook.dao.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author anthonyzero
 * @since 2019-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Integer userId;

    @TableField("order_no")
    private Long orderNo;

    /**
     * 商品id
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 商品图片地址
     */
    @TableField("product_image")
    private String productImage;

    /**
     * 生成订单时的商品单价，单位是元,保留两位小数
     */
    @TableField("current_unit_price")
    private BigDecimal currentUnitPrice;

    /**
     * 商品数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * 商品总价,单位是元,保留两位小数
     */
    @TableField("total_price")
    private BigDecimal totalPrice;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;


}
