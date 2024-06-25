package com.buni.bus.rabbitmq;

import com.buni.bus.constant.CommonConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.Map;

/**
 * ma配置类
 *
 * @author zp.wei
 * @date 2024/6/7 11:04
 */
@Configuration
public class RabbitMqConfig {

    @Lazy
    @Autowired
    private RabbitAdmin rabbitAdmin;


    /**
     * 创建初始化RabbitAdmin对象
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }


    /***************************************直连交换机-支持延时消息*******************************************/


    /**
     * direct交换机
     *
     * @return
     */
    @Bean
    public Exchange directExchange() {
        Map<String, Object> args = new HashMap<>(1);
        args.put(CommonConstant.X_DELAYED_TYPE, CommonConstant.DIRECT);
        return new CustomExchange(CommonConstant.DIRECT_EXCHANGE_NAME, CommonConstant.X_DELAYED_MESSAGE, true, false, args);
    }


    /**
     * direct队列
     *
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(CommonConstant.DIRECT_QUEUE, true);
    }


    /**
     * direct队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(CommonConstant.DIRECT_ROUTING_KEY).noargs();
    }


    /***************************************扇形交换机-支持延时消息*******************************************/


    @Bean
    public Exchange fanoutExchange() {
        Map<String, Object> args = new HashMap<>(1);
        args.put(CommonConstant.X_DELAYED_TYPE, CommonConstant.FANOUT);
        return new CustomExchange(CommonConstant.FANOUT_EXCHANGE_NAME, CommonConstant.X_DELAYED_MESSAGE, true, false, args);
    }


    @Bean
    public Queue queueOne() {
        return new Queue(CommonConstant.FANOUT_QUEUE_ONE);
    }


    @Bean
    public Queue queueTwo() {
        return new Queue(CommonConstant.FANOUT_QUEUE_TWO);
    }


    @Bean
    public Binding bindingOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange()).with("").noargs();
    }


    @Bean
    public Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange()).with("").noargs();
    }


    /**
     * 创建交换机和对列
     */
    @Bean
    public boolean createExchangeQueue() {
        // 直连交换机
        rabbitAdmin.declareExchange(directExchange());
        rabbitAdmin.declareQueue(directQueue());
        // 扇形交换机
        rabbitAdmin.declareExchange(fanoutExchange());
        rabbitAdmin.declareQueue(queueOne());
        rabbitAdmin.declareQueue(queueTwo());
        return true;
    }


}
