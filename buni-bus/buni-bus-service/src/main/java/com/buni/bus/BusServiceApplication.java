package com.buni.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableDiscoveryClient
@EnableTransactionManagement
@SpringBootApplication
public class BusServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BusServiceApplication.class, args);
        System.out.println("---------------消息服务启动成功，端口号：" + context.getEnvironment().getProperty("server.port") + "---------------");
    }

}
