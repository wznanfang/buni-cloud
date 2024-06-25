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
     * 直连交换机发送消息
     * 支持延时消息
     *
     * @param messageDTO 消息
     * @return boolean
     */
    @Async(ExecutorConfig.EXECUTOR_NAME)
    @Override
    public boolean directMessage(MessageDTO messageDTO) {
        rabbitProducer.directMessage(messageDTO);
        return true;
    }


    /**
     * 扇形交换机发送消息
     * 支持延时消息
     *
     * @param messageDTO 消息
     * @return
     */
    @Override
    public boolean fanoutMessage(MessageDTO messageDTO) {
        rabbitProducer.fanoutMessage(messageDTO);
        return true;
    }


}
