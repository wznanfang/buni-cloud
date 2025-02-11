package com.buni.user.controller;

import com.buni.framework.util.Result;
import com.buni.user.constant.CommonConstant;
import com.buni.user.log.SysLogRecord;
import com.buni.user.vo.login.LoginVO;
import com.buni.user.dto.login.UserLoginVO;
import com.buni.user.service.SysLoginService;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zp.wei
 * @date 2023/10/20 15:44
 */
@Tag(name = "登录管理")
@ApiSort(1)
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SysLoginController {

    @Resource
    private SysLoginService sysLoginService;


    /**
     * 用户登录
     *
     * @param loginVO 用户登录信息
     * @return 令牌
     */
    @Operation(summary = "用户登录")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "用户登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody @Validated LoginVO loginVO) {
        return Result.ok(sysLoginService.login(loginVO));
    }


    /**
     * 退出登录
     *
     * @return
     */
    @Operation(summary = "退出登录")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "退出登录")
    @PostMapping("/loginOut")
    public Result<Boolean> loginOut() {
        return Result.ok(sysLoginService.loginOut());
    }


}
