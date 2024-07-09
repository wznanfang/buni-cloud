package com.buni.bus.service.impl;

import com.buni.bus.dto.MessageDTO;
import com.buni.bus.rabbitmq.RabbitMqProducer;
import com.buni.bus.service.RabbitMqDubboService;
import com.buni.framework.constant.CommonConstant;
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
    private RabbitMqProducer rabbitMqProducer;


    /**
     * 直连交换机发送消息
     * 支持延时消息
     *
     * @param messageDTO 消息
     * @return boolean
     */
    @Async(CommonConstant.NORMAL_EXECUTOR_NAME)
    @Override
    public boolean directMessage(MessageDTO messageDTO) {
        rabbitMqProducer.directMessage(messageDTO);
        return true;
    }


    /**
     * 扇形交换机发送消息
     * 支持延时消息
     *
     * @param messageDTO 消息
     * @return
     */
    @Async(CommonConstant.NORMAL_EXECUTOR_NAME)
    @Override
    public boolean fanoutMessage(MessageDTO messageDTO) {
        rabbitMqProducer.fanoutMessage(messageDTO);
        return true;
    }


}
