package com.buni.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zp.wei
 * @date 2023/9/21 9:29
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    RESULT_SUCCESS(true, 200, "请求成功"),
    FORBIDDEN(false, 400, "授权失败"),
    UNAUTHORIZED(false, 401, "未经授权的请求"),
    INVALID_TOKEN(false, 402, "无效的token"),
    ACCESS_DENIED(false, 403, "无权访问"),
    SYSTEM_ERROR(false, 500, "请求失败"),
    FREQUENT_VISITS(false, 100001, "访问频繁，请稍后再试"),
    ;

    private final Boolean success;

    private final Integer code;

    private final String message;


}
