package com.buni.bus.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;


/**
 * @author zp.wei
 * @date 2024/6/7 10:06
 */
@Slf4j
@Configuration
public class RabbitListenerConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;


    /**
     * 设置生产者消息确认监听，当消息成功发到交换机 ack = true，没有发送到交换机 ack = false
     */
    @PostConstruct
    public void enableConfirmCallback() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.error("生产者发送消息失败,失败原因:{}", cause);
            }
        });
    }


}
