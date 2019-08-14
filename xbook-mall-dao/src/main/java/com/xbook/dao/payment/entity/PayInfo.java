package com.xbook.dao.payment.entity;

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
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 订单号
     */
    @TableField("order_no")
    private Long orderNo;

    /**
     * 支付平台:1-支付宝,2-微信
     */
    @TableField("pay_platform")
    private Integer payPlatform;

    /**
     * 支付宝支付流水号
     */
    @TableField("platform_number")
    private String platformNumber;

    /**
     * 支付宝支付状态
     */
    @TableField("platform_status")
    private String platformStatus;

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
