package com.buni.bus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("消息管理")
                .version("1.0")
                .description("消息管理")
                .termsOfService("https://www.buni.com")
                .contact(new Contact().name("不逆").email("wzp3821@163.com").url("https://www.buni.com"))
                .license(new License().name("Apache 2.0").url("https://www.buni.com")));
    }


}
