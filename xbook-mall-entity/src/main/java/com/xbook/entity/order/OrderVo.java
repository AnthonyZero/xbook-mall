package com.xbook.entity.order;

import com.xbook.entity.user.ShippingVo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderVo implements Serializable {

    private Long orderNo;

    private BigDecimal payment;

    private Integer paymentType;

    private String paymentTypeDesc;

    private Integer postage;

    private Integer status;


    private String statusDesc;

    private String paymentTime;


    private String sendTime;

    private String endTime;

    private String closeTime;

    private String createTime;

    //订单的明细
    private List<OrderItemVo> orderItemVoList;

    private String imageHost;
    private String receiverName;

    private Integer shippingId;
    private ShippingVo shippingVo;
}
