package com.buni.user.controller;

import com.buni.framework.util.Result;
import com.buni.user.log.SysLogRecord;
import com.buni.user.vo.login.LoginVO;
import com.buni.user.vo.login.UserLoginVO;
import com.buni.user.constant.CommonConstant;
import com.buni.user.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zp.wei
 * @date 2023/10/20 15:44
 */
@RestController
@RequestMapping(value = "/v1")
public class LoginController {

    @Resource
    private LoginService loginService;


    /**
     * 用户登录
     *
     * @param loginVO 用户登录信息
     * @return 令牌
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "用户登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody @Validated LoginVO loginVO) {
        return Result.ok(loginService.login(loginVO));
    }


    /**
     * 退出登录
     *
     * @return
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "退出登录")
    @PostMapping("/loginOut")
    public Result<Boolean> loginOut() {
        return Result.ok(loginService.loginOut());
    }


}
