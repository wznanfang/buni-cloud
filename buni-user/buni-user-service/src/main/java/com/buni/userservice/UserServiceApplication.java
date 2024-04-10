package com.buni.userservice;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan(value = "com.buni.userservice.mapper")
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.buni.fileapi.service"}) // 指定Feign客户端所在的包路径
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        System.out.println("--------------------启动成功--------------------");
    }

}
