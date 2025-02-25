package com.buni.framework.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zp.wei
 * @date 2023/9/19 11:04
 */
@Configuration
public class RedisConfig {

    /**
     * 配置redisTemplate
     *
     * @param factory {@link LettuceConnectionFactory}
     * @return {@link RedisTemplate}<{@link String}, {@link Object}>
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // 设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化方式json
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置hash的key的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置hash的value的序列化方式
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 使配置生效
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}
