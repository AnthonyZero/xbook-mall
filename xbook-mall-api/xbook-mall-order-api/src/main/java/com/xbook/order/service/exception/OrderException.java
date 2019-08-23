package com.xbook.order.service.exception;

import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.exception.BaseException;

public class OrderException extends BaseException {
    public OrderException() {};

    public OrderException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }
}
