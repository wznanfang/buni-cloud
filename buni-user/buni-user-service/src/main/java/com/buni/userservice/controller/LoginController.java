package com.buni.userservice.controller;

import com.buni.buniframework.util.Result;
import com.buni.buniuserapi.log.SysLogRecord;
import com.buni.usercommon.vo.login.LoginVO;
import com.buni.usercommon.vo.login.UserLoginVO;
import com.buni.userservice.constant.CommonConstant;
import com.buni.userservice.service.UserService;
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
    private UserService userService;


    /**
     * 用户登录
     *
     * @param loginVO 用户登录信息
     * @return 令牌
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "用户登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody @Validated LoginVO loginVO) {
        return Result.ok(userService.login(loginVO));
    }


    /**
     * 退出登录
     *
     * @return
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "退出登录")
    @PostMapping("/loginOut")
    public Result<Boolean> loginOut() {
        return Result.ok(userService.loginOut());
    }


}
