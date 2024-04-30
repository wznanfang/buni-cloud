package com.buni.userservice.controller;

import com.buni.buniframework.util.Result;
import com.buni.fileapi.service.FileDubboService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class FileController {

    @Resource
    private FileDubboService fileDubboService;


    /**
     * minio数据上传
     * todo 调用feign服务，一直报错404，后续再看怎么解决
     *
     * @param filename 文件名字
     * @return 链接
     */
    @GetMapping("/file/{filename}")
    public Result<String> fileUpload(@PathVariable("filename") String filename) {
        String url = fileDubboService.preview(filename);
        return Result.ok(url);
    }

}
