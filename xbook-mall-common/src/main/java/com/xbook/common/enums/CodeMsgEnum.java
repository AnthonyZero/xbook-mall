package com.xbook.common.enums;


import lombok.Getter;

@Getter
public enum CodeMsgEnum {

    //用作session失效或过期
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"), //无操作权限
    BAD_REQUEST(400, "Bad Request"),

    //通用异常 5001xx
    SERVER_ERROR(500100, "服务端异常！请联系管理员"),
    BIND_ERROR(500101, "参数校验异常：%s!"),
    REQUEST_ILLEGAL(500102, "请求非法！"),
    ACCESS_LIMIT_REACHED(500103, "访问太频繁！"),
    VERIFYCODE_ERROR(500104, "验证码错误或已失效"),
    PARAMETER_ERROR(500105, "参数错误"),
    PARAMETER_NOTEXIST(500106, "请输入必填参数"),

    //用户模块 5002XX
    SESSION_ERROR(500210, "Session不存在或者已经失效！"),
    PASSWORD_EMPTY(500211, "登录密码不能为空！"),
    MOBILE_EMPTY(500212, "手机号码不能为空！"),
    MOBILE_ERROR(500213, "手机号码格式错误！"),
    MOBILE_NOT_EXIST(500214, "用户不存在！"),
    PASSWORD_ERROR(500215, "密码错误！"),
    INCORRECT_PASSWORD(500216, "用户名或密码错误"),
    LOCKED_ACCOUNT(500217, "账号已被锁定"),
    USERNAME_EXIST(500208, "用户已存在"),
    EMAIL_EXIST(500208, "邮箱已存在"),

    ;
    private int code;
    private String msg;

    CodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsgEnum fillArgs(Object... objects) {
        int code = this.code;
        //格式化错误信息 填充后面字符串（参数校验异常：%s!）
        String message = String.format(this.msg, objects);
        this.msg = message;
        return this;
    }
}
