package com.buni.file;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@EnableDubbo
@SpringBootApplication
public class FileServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FileServiceApplication.class, args);
        System.out.println("---------------文件服务启动成功，端口号：" + context.getEnvironment().getProperty("server.port") + "---------------");
    }

}
