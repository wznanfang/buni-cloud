package com.buni.user.controller;

import com.buni.file.FileDubboService;
import com.buni.framework.util.Result;
import com.buni.user.service.CommonService;
import com.buni.user.vo.common.QrCodeVO;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zp.wei
 * @date 2024/5/30 14:46
 */
@Slf4j
@Tag(name = "公共接口管理")
@ApiSort(5)
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class CommonController {

    @DubboReference
    private FileDubboService fileDubboService;
    @Resource
    private CommonService commonService;


    /**
     * minio数据上传
     *
     * @param filename 文件名字
     * @return 链接
     */
    @Deprecated
    @Operation(summary = "文件预览", description = "仅作dubbo接口测试使用")
    @GetMapping("/preview")
    public Result<String> preview(@RequestParam("filename") String filename) {
        String url = fileDubboService.preview(filename);
        return Result.ok(url);
    }


    /**
     * 获取验证码
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getCode")
    @Operation(summary = "获取验证码")
    public Result getCode() {
        return Result.ok(commonService.getCode());
    }


    /**
     * 根据数据生成二维码
     *
     * @throws IOException
     */
    @GetMapping("getQrCode")
    @Operation(summary = "根据传过来的数据生成二维码")
    public void QrCode(@ParameterObject QrCodeVO qrCodeVO, HttpServletResponse response) {
        commonService.CreateQrCode(qrCodeVO, response);
    }


}
