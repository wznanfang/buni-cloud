package com.buni.usercommon.enums;

/**
 * @author zp.wei
 * @date 2023/9/21 9:29
 */
public enum ErrorEnum {

    USER_EXISTS(300001, "用户名或手机号已存在"),
    USER_PASSWORD_ERROR(300002, "账号或密码错误"),
    USER_NOT_EXISTS(300003, "用户信息不存在"),
    USER_FORBIDDEN(300004, "用户已被禁用"),
    PHONE_ERROR(300005, "电话号码不正确"),

    /**
     * 权限相关提示
     */
    AUTHORITY_EXISTS(310001, "权限已存在"),
    AUTHORITY_NOT_EXISTS(310002, "权限不存在"),

    /**
     * 角色相关提示
     */
    ROLE_EXISTS(320001, "角色已存在"),
    ROLE_NOT_EXISTS(320002, "角色不存在"),
    ;

    private final Integer code;

    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
