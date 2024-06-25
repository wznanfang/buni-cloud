package com.buni.bus.service;

import com.buni.bus.dto.MessageDTO;

/**
 * @author zp.wei
 * @date 2024/6/14 8:47
 */
public interface RabbitMqDubboService {


    /**
     * 直连交换机发送消息
     *
     * @param messageDTO 消息
     * @return boolean
     */
    boolean directMessage(MessageDTO messageDTO);


    /**
     * 扇形交换机发送消息
     *
     * @param messageDTO 消息
     * @return boolean
     */
    boolean fanoutMessage(MessageDTO messageDTO);


}
