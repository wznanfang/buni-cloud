package com.buni.usercommon.enums;

/**
 * @author zp.wei
 * @date 2023/9/21 9:29
 */
public enum UserErrorEnum {

    USER_EXISTS(300001, "用户名或手机号已存在"),
    USER_PASSWORD_ERROR(300002, "账号或密码错误"),
    USER_NOT_EXISTS(300003, "用户信息不存在"),
    PHONE_ERROR(300004, "电话号码不正确"),
    ;

    private final Integer code;

    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private UserErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
