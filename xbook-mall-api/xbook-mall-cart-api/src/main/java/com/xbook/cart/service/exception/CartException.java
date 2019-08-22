package com.xbook.cart.service.exception;


import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.exception.BaseException;

public class CartException extends BaseException {

    public CartException() {}
    public CartException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }
}
