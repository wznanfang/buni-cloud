package com.buni.ai.constant;

import lombok.Getter;

/**
 * @author zp.wei
 * @date 2024/7/31 13:38
 */
@Getter
public class CommonConstant {

    /**
     * 讯飞星火大模型相关配置
     */
    public static final Integer TOKENS = 4096;
    public static final Double TEMPERATURE = 0.7;


    /**
     * 千帆大模型相关配置
     */
    public static final String MODEL = "ERNIE-Speed-128K";
    public static final String ROLE = "user";
    public static final String ASSISTANT = "assistant";

}
