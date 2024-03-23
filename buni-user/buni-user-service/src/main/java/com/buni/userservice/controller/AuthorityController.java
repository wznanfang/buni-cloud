package com.buni.userservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.buniframework.util.Result;
import com.buni.buniuserapi.log.SysLogRecord;
import com.buni.userservice.constant.CommonConstant;
import com.buni.userservice.service.AuthorityService;
import com.buni.userservice.vo.authority.*;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【authority(权限)】的数据库操作Controller
 * @createDate 2023-09-25 13:45:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class AuthorityController {

    private final AuthorityService authorityService;


    /**
     * 新增权限
     *
     * @param addVO 权限信息
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "新增权限")
    @PostMapping("/authority")
    public Result<Boolean> save(@RequestBody @Validated AddVO addVO) {
        return Result.ok(authorityService.save(addVO));
    }


    /**
     * 修改权限
     *
     * @param updateVO 权限信息
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "修改权限")
    @PutMapping("/authority")
    public Result<Boolean> update(@RequestBody @Validated UpdateVO updateVO) {
        return Result.ok(authorityService.update(updateVO));
    }


    /**
     * 删除权限
     *
     * @param id 权限信息id
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "删除权限")
    @DeleteMapping("/authority/{id:\\d+}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(authorityService.delete(id));
    }


    /**
     * 批量删除权限
     *
     * @param batchIds 权限信息id
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "批量删除权限")
    @DeleteMapping("/authority")
    public Result<Boolean> BatchDelete(@RequestBody BatchIds batchIds) {
        return Result.ok(authorityService.batchDelete(batchIds));
    }


    /**
     * 根据id查询权限
     *
     * @param id id
     * @return 权限信息
     */
    @GetMapping("/authority/{id:\\d+}")
    public Result<AuthorityInfoVO> findById(@PathVariable Long id) {
        return Result.ok(authorityService.findById(id));
    }


    /**
     * 分页查询
     *
     * @param pageVO 查询条件
     * @return 权限信息
     */
    @GetMapping("/authority")
    public Result<IPage<AuthorityGetVO>> page(PageVO pageVO) {
        return Result.ok(authorityService.findPage(pageVO));
    }


}
