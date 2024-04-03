package com.buni.bunifile.config;

import com.buni.bunifile.properties.MinioProperties;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zp.wei
 * @date 2024/4/03 20:23
 */
@Configuration
public class MinioConfig {

    @Resource
    private MinioProperties minioProperties;

    /**
     * 获取MinioClient
     */
    @Bean(name = "minioClient")
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }


}
