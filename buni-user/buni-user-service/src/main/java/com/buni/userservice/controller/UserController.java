package com.buni.userservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.buniframework.util.Result;
import com.buni.usercommon.vo.LoginVO;
import com.buni.usercommon.vo.UserLoginVO;
import com.buni.userservice.service.UserService;
import com.buni.userservice.vo.user.AddVO;
import com.buni.userservice.vo.user.PageVO;
import com.buni.userservice.vo.user.UserInfoVO;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zp.wei
 * @date 2023/9/18 17:17
 */
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 用户登录
     *
     * @param loginVO 用户登录信息
     * @return 令牌
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody @Validated LoginVO loginVO) {
        return Result.ok(userService.login(loginVO));
    }


    /**
     * 新增用户
     *
     * @param addVO 用户信息
     * @return true/false
     */
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody @Validated AddVO addVO) {
        return Result.ok(userService.save(addVO));
    }


    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result<UserInfoVO> register(@PathVariable Long id) {
        return Result.ok(userService.findById(id));
    }


    @GetMapping("/page")
    public Result<IPage<UserInfoVO>> page(PageVO pageVO) {
        return Result.ok(userService.findPage(pageVO));
    }


}
