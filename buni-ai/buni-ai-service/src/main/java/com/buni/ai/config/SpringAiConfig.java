package com.buni.ai.config;

import org.springframework.context.annotation.Configuration;

/**
 * Spring AI配置类
 * 
 * @author zp.wei
 * @date 2024/12/19
 */
@Configuration
public class SpringAiConfig {

    // 暂时注释掉Spring AI相关配置，因为包路径有问题
    /*
    @Bean
    @Qualifier("qianfanChatClient")
    @ConditionalOnBean(name = "qianfanChatModel")
    public ChatClient qianfanChatClient(@Qualifier("qianfanChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean
    @Qualifier("sparkChatClient")
    @ConditionalOnBean(name = "sparkChatModel")
    public ChatClient sparkChatClient(@Qualifier("sparkChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean
    @Primary
    @Qualifier("defaultChatClient")
    @ConditionalOnBean(name = "qianfanChatClient")
    public ChatClient defaultChatClient(@Qualifier("qianfanChatClient") ChatClient chatClient) {
        return chatClient;
    }
    */
} 