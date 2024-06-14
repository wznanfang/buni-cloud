package com.buni.bus.service.impl;

import com.buni.bus.dto.MessageDTO;
import com.buni.bus.service.RabbitMqService;
import com.buni.bus.rabbitmq.RabbitProducer;
import com.buni.framework.config.executor.ExecutorConfig;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;


/**
 * @Author: zp.wei
 * @DATE: 2020/11/17 14:26
 */
@Slf4j
@Configuration
public class RabbitServiceImpl implements RabbitMqService {

    @Resource
    private RabbitProducer rabbitProducer;


    /**
     * 发送消息
     *
     * @param message
     */
    @Async(ExecutorConfig.EXECUTOR_NAME)
    @Override
    public void directDefaultSend(String message) {
        rabbitProducer.directDefaultSend(message);
    }

    /**
     * 发送消息
     *
     * @param messageDTO
     */
    @Async(ExecutorConfig.EXECUTOR_NAME)
    @Override
    public void sendMessage(MessageDTO messageDTO) {
        rabbitProducer.sendMessage(messageDTO);
    }


}