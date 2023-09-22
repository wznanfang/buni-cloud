package com.buni.buniframework.constant;

import lombok.Data;

/**
 * @author zp.wei
 * @date 2023/9/19 17:48
 */
@Data
public class CommonConstant {

    /**
     * token过期时间(毫秒)
     */
    public static final Long EXPIRE_TIME_MS = 1800 * 1000L;

    /**
     * token前缀
     */
    public static final String TOKEN_REDIS_KEY = "token:";

    /**
     * 空字符串
     */
    public static final String EMPTY_STR = "";

    /**
     * 冒号
     */
    public static final String COLON = ":";

    /**
     * token
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * token前缀
     */
    public static final String PREFIX = "bearer ";

    /**
     * 网关层添加header
     */
    public static final String GATEWAY_KEY = "gateway_key";

    /**
     * 用户id
     */
    public static final String USER_ID = "user_id";

    /**
     * 用户名字
     */
    public static final String USER_NAME = "username";

}
