package com.buni.ai.config;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.core.auth.Auth;
import com.buni.ai.properties.QianFanProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zp.wei
 * @date 2024/7/31 9:55
 */
@Configuration
public class QiaFanConfig {

    @Resource
    private QianFanProperties qianFanProperties;

    @Bean("qianfan")
    public Qianfan qianfan() {
        return new Qianfan(Auth.TYPE_OAUTH,qianFanProperties.getApiKey(), qianFanProperties.getSecretKey());
    }


}
