package com.buni.user.controller;

import com.buni.framework.util.Result;
import com.buni.file.FileDubboService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class FileController {

    @DubboReference
    private FileDubboService fileDubboService;


    /**
     * minio数据上传
     *
     * @param filename 文件名字
     * @return 链接
     */
    @GetMapping("/file")
    public Result<String> fileUpload(@RequestParam("filename") String filename) {
        String url = fileDubboService.preview(filename);
        return Result.ok(url);
    }

}
