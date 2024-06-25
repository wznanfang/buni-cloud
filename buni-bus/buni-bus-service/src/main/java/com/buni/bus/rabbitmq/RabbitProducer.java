package com.buni.bus.rabbitmq;

import com.buni.bus.constant.CommonConstant;
import com.buni.bus.dto.MessageDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Configuration;


/**
 * 生产者
 *
 * @author zp.wei
 * @date 2024/6/7 10:32
 */
@Slf4j
@Configuration
public class RabbitProducer {

    @Resource
    private AmqpTemplate amqpTemplate;


    /**
     * 默认交换机
     * 支持延时消息
     *
     * @param messageDTO 消息
     */
    public void directMessage(MessageDTO messageDTO) {
        log.info("【直连交换机生产者发送消息】---------: '{}'", messageDTO.getMessage());
        amqpTemplate.convertAndSend(CommonConstant.DIRECT_EXCHANGE_NAME, CommonConstant.DIRECT_ROUTING_KEY, messageDTO.getMessage(), msg -> {
            msg.getMessageProperties().setHeader(CommonConstant.X_DELAY, messageDTO.getDelayTime());
            return msg;
        });
    }


    /**
     * 扇形交换机发送消息
     * 支持延时消息
     *
     * @param messageDTO
     */
    public void fanoutMessage(MessageDTO messageDTO) {
        log.info("【扇形交换机生产者发送消息】---------: '{}'", messageDTO.getMessage());
        amqpTemplate.convertAndSend(CommonConstant.FANOUT_EXCHANGE_NAME, CommonConstant.EMPTY_STRING, messageDTO.getMessage(), msg -> {
            msg.getMessageProperties().setHeader(CommonConstant.X_DELAY, messageDTO.getDelayTime());
            return msg;
        });
    }


}
