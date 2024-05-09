package com.buni.file.service.impl;

import com.buni.file.FileDubboService;
import com.buni.file.properties.MinioProperties;
import com.buni.file.util.MinioUtil;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
@AllArgsConstructor
@NoArgsConstructor
public class FileDubboServiceImpl implements FileDubboService {


    @Resource
    private MinioProperties minioProperties;
    @Resource
    private MinioUtil minioUtil;


    /**
     * 获取文件链接
     *
     * @param filename 文件名
     * @return 链接
     */
    @Override
    public String preview(String filename) {
        return minioUtil.getObjectURL(minioProperties.getBucket(), filename, 3);
    }


}
