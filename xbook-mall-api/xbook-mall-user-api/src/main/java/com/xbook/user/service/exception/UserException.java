package com.xbook.user.service.exception;

import com.xbook.common.enums.CodeMsgEnum;
import com.xbook.common.exception.BaseException;

public class UserException extends BaseException {
    public UserException() {};

    public UserException(CodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum);
    }
}
