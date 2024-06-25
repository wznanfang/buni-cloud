package com.buni.bus.constant;

/**
 * @author zp.wei
 * @date 2024/6/7 10:32
 */
public class CommonConstant {

    public static final String EMPTY_STRING = "";
    public static final String X_DELAY = "x-delay";
    public static final String X_DELAYED_TYPE = "x-delayed-type";
    public static final String X_DELAYED_MESSAGE = "x-delayed-message";
    public static final String DIRECT = "direct";
    public static final String FANOUT = "fanout";

    /**
     * 直连交换机配置
     */
    public static final String DIRECT_EXCHANGE_NAME = "buni.direct";
    public static final String DIRECT_QUEUE = "buni.queue";
    public static final String DIRECT_ROUTING_KEY = "buni.routing";

    /**
     * 扇形交换机配置
     */
    public static final String FANOUT_EXCHANGE_NAME = "buni.fanout";
    public static final String FANOUT_QUEUE_ONE = "buni.fanout.queue.one";
    public static final String FANOUT_QUEUE_TWO = "buni.fanout.queue.two";


}
