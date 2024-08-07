package com.buni.bus.service.impl;

import com.buni.bus.dto.MessageDTO;
import com.buni.bus.rabbitmq.RabbitMqProducer;
import com.buni.bus.service.RabbitMqService;
import com.buni.framework.config.executor.ExecutorConfig;
import com.buni.framework.constant.CommonConstant;
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
    private RabbitMqProducer rabbitMqProducer;


    /**
     * 默认交换机发送消息
     *
     * @param messageDTO 消息
     */
    @Async(CommonConstant.NORMAL_EXECUTOR_NAME)
    @Override
    public void directMessage(MessageDTO messageDTO) {
        rabbitMqProducer.directMessage(messageDTO);
    }


    /**
     * 发送消息
     *
     * @param messageDTO
     */
    @Async(CommonConstant.NORMAL_EXECUTOR_NAME)
    @Override
    public void fanoutMessage(MessageDTO messageDTO) {
        rabbitMqProducer.fanoutMessage(messageDTO);
    }


}
