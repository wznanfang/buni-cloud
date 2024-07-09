package com.buni.framework.constant;

import lombok.Data;

import java.io.File;
import java.util.List;

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
     * 逗号
     */
    public static final String COMMA = ",";

    /**
     * 斜杠
     */
    public static final String SLASH = File.separator;

    /**
     * 正斜杠
     */
    public static final String FORWARD_SLASH = "/";

    /**
     * 横杠
     */
    public static final String LINE = "-";

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

    /**
     * url
     */
    public static final String URL = "url";

    /**
     * 客户端标识
     */
    public static final String USER_AGENT = "User-Agent";

    /**
     * 限流前缀
     */
    public static final String RATE_LIMITER = "rate:limit:";


    /**
     * 普通常量
     */
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer THIRTY = 30;
    public static final Integer FIFTY = 50;
    public static final Integer SIXTY = 60;
    public static final Integer ONE_HUNDRED = 100;

    /**
     * IP地址相关常量
     */
    public static final String FORWARDED = "x-forwarded-for";
    public static final String UNKNOWN = "unknown";
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    public static final String X_REAL_IP = "X-Real-IP";


    /**
     * 线程池相关配置
     */
    public final static String NORMAL_EXECUTOR_NAME = "normalThreadPoolExecutor";
    public final static String LARGE_EXECUTOR_NAME = "largeThreadPoolExecutor";
    public final static String EXECUTOR_NAME_PREFIX = "buni-cloud";
    public final static Integer PROCESSORS = Runtime.getRuntime().availableProcessors();

}
