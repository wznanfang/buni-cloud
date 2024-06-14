package com.buni.bus.service;

import com.buni.bus.dto.MessageDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zp.wei
 * @date 2024/6/14 8:47
 */
public interface RabbitMqDubboService {


    /**
     * 默认交换机发送消息
     *
     * @param message 消息
     * @return boolean
     */
    boolean defaultSendMessage(String message);

    /**
     * 延时交换机发送消息
     *
     * @param messageDTO 消息
     * @return boolean
     */
    boolean sendMessage(@RequestBody MessageDTO messageDTO);


}
