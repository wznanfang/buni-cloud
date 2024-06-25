package com.buni.bus.service;

import com.buni.bus.dto.MessageDTO;

/**
 * @author zp.wei
 * @date 2024/6/7 10:37
 */
public interface RabbitMqService {

    /**
     * 默认交换机发送消息
     *
     * @param messageDTO 消息
     */
    void directMessage(MessageDTO messageDTO);

    /**
     * 发送消息
     *
     * @param messageDTO
     */
    void fanoutMessage(MessageDTO messageDTO);

}
