package com.buni.buniframework.enums;

/**
 * @author zp.wei
 * @date 2023/9/21 9:29
 */
public enum ResultEnum {

    RESULT_SUCCESS(true, 200, "请求成功"),
    FORBIDDEN(false, 400, "授权失败"),
    UNAUTHORIZED(false, 401, "未经授权的请求"),
    INVALID_TOKEN(false, 402, "无效的token"),
    ACCESS_DENIED(false, 403, "无权访问"),
    SYSTEM_ERROR(false, 500, "系统错误"),
    ;

    private final Boolean success;

    private final Integer code;

    private final String message;

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private ResultEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }


}
