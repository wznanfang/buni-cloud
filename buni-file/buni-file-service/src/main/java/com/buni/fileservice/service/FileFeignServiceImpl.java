package com.buni.fileservice.service;

import com.buni.fileapi.service.FileFeignService;
import com.buni.fileservice.properties.MinioProperties;
import com.buni.fileservice.util.MinioUtil;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class FileFeignServiceImpl implements FileFeignService {


    @Resource
    private MinioProperties minioProperties;
    @Resource
    private MinioUtil minioUtil;

    /**
     * 文件上传
     *
     * @return 文件预览地址
     */
    @Override
    public String upload(MultipartFile file) {
        return minioUtil.uploadFile(minioProperties.getBucket(), file);
    }


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
