package com.buni.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.framework.util.Result;
import com.buni.user.constant.CommonConstant;
import com.buni.user.log.SysLogRecord;
import com.buni.user.service.UserService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.user.*;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zp.wei
 * @date 2023/9/18 17:17
 */
@Tag(name = "用户管理")
@ApiSort(2)
@AllArgsConstructor
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
    @Operation(summary = "新增用户")
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
    @Operation(summary = "编辑用户")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "编辑用户")
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
    @Operation(summary = "启用-禁用用户")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "启用-禁用用户")
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
    @Operation(summary = "删除用户")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "删除用户")
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(userService.delete(id));
    }


    /**
     * 根据id集合批量删除用户
     *
     * @param idVOs 用户id
     * @return true/false
     */
    @Operation(summary = "批量删除用户")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "批量删除用户")
    @DeleteMapping("/batchDelete")
    public Result<Boolean> batchDelete(@RequestBody @Validated IdVOs idVOs) {
        return Result.ok(userService.batchDelete(idVOs));
    }


    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Operation(summary = "查询用户信息")
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
    @Operation(summary = "分页查询用户信息")
    @GetMapping("/user")
    public Result<IPage<UserGetVO>> page(@ParameterObject PageVO pageVO) {
        return Result.ok(userService.findPage(pageVO));
    }


}
