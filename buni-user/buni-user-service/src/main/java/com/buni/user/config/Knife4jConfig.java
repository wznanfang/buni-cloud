package com.buni.user.config;

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
                .title("SpringBoot API 管理")
                .contact(new Contact().name("N_007").email("xxxx@163.com").url("https://blog.csdn.net/N_007"))
                .version("1.0")
                .description("SpringBoot 集成 Knife4j 示例")
                .license(new License().name("Apache 2.0")));
    }

}
