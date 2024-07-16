package com.buni.user.controller;

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
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Resource
    private CommonService commonService;


    @GetMapping("/getCode")
    @Operation(summary = "获取验证码")
    public Result getCode() {
        return Result.ok(commonService.getCode());
    }


    @GetMapping("getQrCode")
    @Operation(summary = "根据传过来的数据生成二维码")
    public void QrCode(@ParameterObject QrCodeVO qrCodeVO, HttpServletResponse response) {
        commonService.createQrCode(qrCodeVO, response);
    }


}
