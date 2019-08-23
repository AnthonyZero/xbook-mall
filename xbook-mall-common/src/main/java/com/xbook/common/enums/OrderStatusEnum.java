package com.xbook.common.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    CANCELED(0,"已取消"),
    NO_PAY(10,"未支付"),
    PAID(20,"已付款"),
    SHIPPED(40,"已发货"),
    ORDER_SUCCESS(50,"订单完成"),
    ORDER_CLOSE(60,"订单关闭");

    private String msg;
    private int code;

    OrderStatusEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }


    public static OrderStatusEnum codeOf(int code){
        for(OrderStatusEnum orderStatusEnum : values()){
            if(orderStatusEnum.getCode() == code){
                return orderStatusEnum;
            }
        }
        throw new RuntimeException("没有找到对应的枚举");
    }
}
