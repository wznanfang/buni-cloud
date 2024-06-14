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
     * @param message 消息
     */
    void directDefaultSend(String message);


    /**
     * 延时交换机发送消息
     *
     * @param messageDTO 消息
     */
    void sendMessage(MessageDTO messageDTO);

}
