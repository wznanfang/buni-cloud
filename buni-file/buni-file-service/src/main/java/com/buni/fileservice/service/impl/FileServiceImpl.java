package com.buni.fileservice.service.impl;

import com.buni.buniframework.util.Result;
import com.buni.fileservice.properties.MinioProperties;
import com.buni.fileservice.service.FileService;
import com.buni.fileservice.util.MinioUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author zp.wei
 * @date 2024/4/30 9:20
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private MinioUtil minioUtil;
    @Resource
    private MinioProperties minioProperties;


    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {
        return minioUtil.uploadFile(minioProperties.getBucket(), file);
    }


    /**
     * 获取文件链接
     *
     * @param filename
     * @return
     */
    @Override
    public String preview(String filename) {
        return minioUtil.getObjectURL(minioProperties.getBucket(), filename, 3);
    }
}
