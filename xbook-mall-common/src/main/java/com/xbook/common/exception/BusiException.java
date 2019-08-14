package com.xbook.common.exception;


import com.xbook.common.enums.CodeMsgEnum;

public class BusiException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private CodeMsgEnum codeMsgEnum;

    public BusiException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum.getMsg());
        this.codeMsgEnum = codeMsgEnum;
    }

    public CodeMsgEnum getCodeMsgEnum() {
        return codeMsgEnum;
    }
}
