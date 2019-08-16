package com.xbook.common.exception;


import com.xbook.common.enums.CodeMsgEnum;

/**
 * 模块业务异常都继承BaseException
 */
public class BaseException extends RuntimeException {
    private CodeMsgEnum codeMsgEnum;

    public BaseException() { }

    public BaseException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum.getMsg());
        this.codeMsgEnum = codeMsgEnum;
    }

    public CodeMsgEnum getCodeMsgEnum() {
        return codeMsgEnum;
    }
}
