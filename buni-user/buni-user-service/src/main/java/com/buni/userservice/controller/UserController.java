package com.buni.userservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.buniframework.util.Result;
import com.buni.buniuserapi.log.SysLogRecord;
import com.buni.userservice.constant.CommonConstant;
import com.buni.userservice.service.UserService;
import com.buni.userservice.vo.user.*;
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
     * 新增用户
     *
     * @param addVO 用户信息
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "注册用户")
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody @Validated AddVO addVO) {
        return Result.ok(userService.save(addVO));
    }


    /**
     * 根据id编辑用户基本信息
     *
     * @param updateVO 用户信息
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "编辑用户基本信息")
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody @Validated UpdateVO updateVO) {
        return Result.ok(userService.update(updateVO));
    }


    /**
     * 根据id 启用/禁用用户
     *
     * @param enableVO 启用/禁用信息
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "启用/禁用用户")
    @PutMapping("/forbidden")
    public Result<Boolean> forbidden(@RequestBody @Validated EnableVO enableVO) {
        return Result.ok(userService.enable(enableVO));
    }


    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "删除用户")
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(userService.delete(id));
    }


    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "查询用户详情")
    @GetMapping("/findById/{id}")
    public Result<UserInfoVO> findById(@PathVariable Long id) {
        return Result.ok(userService.findById(id));
    }


    /**
     * 分页查询
     *
     * @param pageVO 查询条件
     * @return 用户信息
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "分页查询")
    @GetMapping("/page")
    public Result<IPage<UserInfoVO>> page(PageVO pageVO) {
        return Result.ok(userService.findPage(pageVO));
    }


}
