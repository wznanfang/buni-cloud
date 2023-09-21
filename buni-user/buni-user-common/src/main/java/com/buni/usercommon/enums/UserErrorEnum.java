package com.buni.usercommon.enums;

/**
 * @author zp.wei
 * @date 2023/9/21 9:29
 */
public enum UserErrorEnum {

    USER_NOT_EXISTS(100001, "用户信息不存在"),
    USER_EXISTS(100002, "用户名已存在"),
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
