package com.buni.bus.rabbitmq;

import com.buni.bus.properties.CommonProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author zp.wei
 * @date 2024/6/7 11:04
 */
@Configuration
public class RabbitMqConfig {

    @Lazy
    @Autowired
    private RabbitAdmin rabbitAdmin;


    /**
     * direct交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(CommonProperties.DIRECT_DEFAULT_EXCHANGE_NAME, true, false);
    }


    /**
     * direct队列
     *
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(CommonProperties.DIRECT_DEFAULT_QUEUE, true);
    }


    /**
     * direct队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(CommonProperties.DIRECT_DEFAULT_ROUTING_KEY);
    }


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


    /**
     * 创建交换机和对列
     */
    @Bean
    public boolean createExchangeQueue() {
        rabbitAdmin.declareExchange(directExchange());
        rabbitAdmin.declareQueue(directQueue());
        return true;
    }


}
