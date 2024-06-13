package com.buni.bus.service;

import com.buni.bus.dto.MessageDTO;

/**
 * @author zp.wei
 * @date 2024/6/7 10:37
 */
public interface RabbitMqService {

    /**
     * 发送消息
     *
     * @param message
     */
    void directDefaultSend(String message);


    /**
     * 发送消息
     *
     * @param messageDTO
     */
    void sendMessage(MessageDTO messageDTO);

}
