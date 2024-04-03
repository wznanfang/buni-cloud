package com.buni.bunifile.controller;

import com.buni.bunifile.util.MinioUtil;
import com.buni.buniframework.util.Result;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class FileController {

    @Resource
    private MinioUtil minioUtil;


    /**
     * minio数据上传
     * @param file 文件
     * @return 链接
     */
    @PostMapping("/minio")
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file) {
        String url = minioUtil.uploadFile(file);
        //todo 相关信息存入数据库，便于后续数据库和minio映射

        return Result.ok(url);
    }

}
