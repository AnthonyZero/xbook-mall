package com.xbook.product.service.exception;


import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.exception.BaseException;

public class ProductException extends BaseException {

    public ProductException() {}
    public ProductException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }
}
