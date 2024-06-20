package com.buni.bus.rabbitmq;

import com.buni.bus.constant.CommonConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * ma配置类
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


    /***************************************直连交换机*******************************************/


    /**
     * direct交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(CommonConstant.DIRECT_DEFAULT_EXCHANGE_NAME, true, false);
    }


    /**
     * direct队列
     *
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(CommonConstant.DIRECT_DEFAULT_QUEUE, true);
    }


    /**
     * direct队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(CommonConstant.DIRECT_DEFAULT_ROUTING_KEY);
    }


    /***************************************扇形交换机*******************************************/

/*

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(CommonConstant.FANOUT_EXCHANGE_NAME);
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
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }


    @Bean
    public Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }
*/


    /**
     * 创建交换机和对列
     */
    @Bean
    public void createExchangeQueue() {
        // 直连交换机
        rabbitAdmin.declareExchange(directExchange());
        rabbitAdmin.declareQueue(directQueue());
        // 扇形交换机
        /*rabbitAdmin.declareExchange(fanoutExchange());
        rabbitAdmin.declareQueue(queueOne());
        rabbitAdmin.declareQueue(queueTwo());*/
    }


}
