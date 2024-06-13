package com.buni.bus.properties;

/**
 * @author zp.wei
 * @date 2024/6/7 10:32
 */
public class CommonProperties {

    /**
     * 默认mq配置
     */
    public static final String DIRECT_DEFAULT_EXCHANGE_NAME = "buni.default.direct";
    public static final String DIRECT_DEFAULT_QUEUE = "buni.default.queue";
    public static final String DIRECT_DEFAULT_ROUTING_KEY = "buni.default.routing";


    /**
     * 延时mq配置
     */
    public static final String DIRECT_EXCHANGE_NAME = "buni.direct";
    public static final String DIRECT_ROUTING_KEY = "buni.routing";

}
