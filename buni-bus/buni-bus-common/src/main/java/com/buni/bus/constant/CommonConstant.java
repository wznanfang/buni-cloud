package com.buni.bus.constant;

/**
 * @author zp.wei
 * @date 2024/6/7 10:32
 */
public class CommonConstant {

    public static final String EMPTY_STRING = "";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String DELAY = "x-delay";

    /**
     * 直连交换机配置
     */
    public static final String DIRECT_DEFAULT_EXCHANGE_NAME = "buni.default.direct";
    public static final String DIRECT_DEFAULT_QUEUE = "buni.default.queue";
    public static final String DIRECT_DEFAULT_ROUTING_KEY = "buni.default.routing";

    /**
     * 扇形交换机配置
     */
    public static final String FANOUT_EXCHANGE_NAME = "buni.fanout";
    public static final String FANOUT_QUEUE_ONE = "buni.fanout.queue.one";
    public static final String FANOUT_QUEUE_TWO = "buni.fanout.queue.two";

    /**
     * 延时mq配置
     */
    public static final String DIRECT_EXCHANGE_NAME = "buni.direct";
    public static final String DIRECT_ROUTING_KEY = "buni.routing";

}
