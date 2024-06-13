package com.buni.bus.rabbitmq;

import com.buni.bus.dto.MessageDTO;
import com.buni.bus.properties.CommonProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Configuration;


/**
 * @author zp.wei
 * @date 2024/6/7 10:32
 */
@Slf4j
@Configuration
public class RabbitProducer {

    @Resource
    private AmqpTemplate amqpTemplate;


    /**
     * 默认交换机，默认交换队列和路由键
     *
     * @param message
     */
    public void directDefaultSend(String message) {
        log.info("【默认生产者发送消息】---------: '{}'", message);
        amqpTemplate.convertAndSend(CommonProperties.DIRECT_DEFAULT_EXCHANGE_NAME, CommonProperties.DIRECT_DEFAULT_ROUTING_KEY, message);
    }


    /**
     * 自定义路由键
     *
     * @param messageDTO
     */
    public void sendMessage(MessageDTO messageDTO) {
        log.info("【延时生产者发送消息】---------: '{}'", messageDTO.getMessage());
        amqpTemplate.convertAndSend(CommonProperties.DIRECT_EXCHANGE_NAME, CommonProperties.DIRECT_ROUTING_KEY, messageDTO.getMessage(), msg -> {
            //设置延迟时间
            msg.getMessageProperties().setHeader("x-delay", messageDTO.getDelayTime());
            return msg;
        });
    }


}
