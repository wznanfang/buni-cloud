package com.buni.bunigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BuNiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuNiGatewayApplication.class, args);
        System.out.println("--------------------启动成功--------------------");
    }

}
