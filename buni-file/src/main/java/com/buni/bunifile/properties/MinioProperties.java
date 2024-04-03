package com.buni.bunifile.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zp.wei
 * @date 2024/4/03 20:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "buni.minio")
public class MinioProperties {

    /**
     * 连接地址
     */
    private String endpoint;
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;

    /**
     * 桶名
     */
    private String bucket;

}
