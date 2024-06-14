package com.buni.bus.rabbitmq;

import com.buni.bus.constant.CommonConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author zp.wei
 * @date 2024/6/7 16:04
 */
@Slf4j
@Configuration
@Deprecated
public class DirectConsumer {


    @RabbitListener(queues = CommonConstant.DIRECT_DEFAULT_QUEUE)
    public void receive(Message message, Channel channel) {
        log.info("[默认消费者接收消息]---------- '{}'", new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "complain-queue", durable = "true"),
            exchange = @Exchange(value = CommonConstant.DIRECT_EXCHANGE_NAME, delayed = "true"), key = CommonConstant.DIRECT_ROUTING_KEY))
    public void receive2(Message message, Channel channel) {
        log.info("[延时消费者接收消息]---------- '{}'", new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
