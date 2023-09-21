package com.buni.buniframework.constant;

import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 17:48
 */
@Data
public class CommonConstant {

    /**
     * token过期时间
     */
    public static final Long EXPIRE_TIME = 1800 * 1000L;

    /**
     * 空字符串
     */
    public static final String EMPTY_STR = "";

    /**
     * token
     */
    public static final String AUTHORIZATION = "authorization";

    /**
     * 网关层添加header
     */
    public static final String GATEWAY_KEY = "gateway_key";

}
