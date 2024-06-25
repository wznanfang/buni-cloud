package com.buni.bus.rabbitmq;

import com.buni.bus.constant.CommonConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 消费者示例代码
 *
 * @author zp.wei
 * @date 2024/6/7 16:04
 */
@Slf4j
@Configuration
@Deprecated
public class DirectConsumer {


    @RabbitListener(queues = CommonConstant.DIRECT_QUEUE)
    public void directReceive(Message message, Channel channel) {
        log.info("[直连交换机消费者接收消息]---------- '{}'", new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @RabbitListener(queues = CommonConstant.FANOUT_QUEUE_ONE)
    public void fanoutQueueOne(Message message, Channel channel) {
        log.info("[扇形交换机1延时消费者接收消息]---------- '{}'", new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @RabbitListener(queues = CommonConstant.FANOUT_QUEUE_TWO)
    public void fanoutQueueTwo(Message message, Channel channel) {
        log.info("[扇形交换机2延时消费者接收消息]---------- '{}'", new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
