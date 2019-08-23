package com.xbook.common.enums;

import lombok.Getter;

@Getter
public enum PaymentTypeEnum {

    ONLINE_PAY(1,"在线支付");

    PaymentTypeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    private String msg;
    private int code;


    public static PaymentTypeEnum codeOf(int code){
        for(PaymentTypeEnum paymentTypeEnum : values()){
            if(paymentTypeEnum.getCode() == code){
                return paymentTypeEnum;
            }
        }
        throw new RuntimeException("没有找到对应的枚举");
    }
}
