package com.buni.fileservice.service;

import com.buni.fileapi.service.FileFeignService;
import com.buni.fileservice.util.MinioUtil;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class FileFeignServiceImpl implements FileFeignService {


    @Resource
    private MinioUtil minioUtil;

    /**
     * 文件上传
     *
     * @return 文件预览地址
     */
    @Override
    public String upload(MultipartFile file) {
        return minioUtil.uploadFile(file);
    }


}
