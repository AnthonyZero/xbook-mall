package com.xbook.dao.order.entity;

import java.math.BigDecimal;
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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableField("order_no")
    private Long orderNo;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    @TableField("shipping_id")
    private Integer shippingId;

    /**
     * 实际付款金额,单位是元,保留两位小数
     */
    @TableField("payment")
    private BigDecimal payment;

    /**
     * 支付类型,1-在线支付
     */
    @TableField("payment_type")
    private Integer paymentType;

    /**
     * 运费,单位是元
     */
    @TableField("postage")
    private Integer postage;

    /**
     * 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
     */
    @TableField("status")
    private Integer status;

    /**
     * 支付时间
     */
    @TableField("payment_time")
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    @TableField("send_time")
    private LocalDateTime sendTime;

    /**
     * 交易完成时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 交易关闭时间
     */
    @TableField("close_time")
    private LocalDateTime closeTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
