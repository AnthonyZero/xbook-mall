package com.xbook.common.enums;

import lombok.Getter;

@Getter
public enum CartCheckEnum {

    CHECKED(1, "选中"),
    UN_CHECKED(0, "未选中"),

    ;
    private int code;
    private String msg;

    CartCheckEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
