package com.buni.bus.service.impl;

import com.buni.bus.dto.MessageDTO;
import com.buni.bus.rabbitmq.RabbitProducer;
import com.buni.bus.service.RabbitMqDubboService;
import com.buni.framework.config.executor.ExecutorConfig;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zp.wei
 * @date 2024/6/14 8:50
 */
@Slf4j
@Service
@AllArgsConstructor
public class RabbitMqDubboServiceImpl implements RabbitMqDubboService {

    @Resource
    private RabbitProducer rabbitProducer;


    /**
     * 默认交换机发送消息
     *
     * @param message 消息
     * @return boolean
     */
    @Async(ExecutorConfig.EXECUTOR_NAME)
    @Override
    public boolean defaultSendMessage(String message) {
        rabbitProducer.directDefaultSend(message);
        return true;
    }


    /**
     * 延时交换机发送消息
     *
     * @param messageDTO 消息
     * @return boolean
     */
    @Async(ExecutorConfig.EXECUTOR_NAME)
    @Override
    public boolean sendMessage(MessageDTO messageDTO) {
        rabbitProducer.sendMessage(messageDTO);
        return true;
    }


}
