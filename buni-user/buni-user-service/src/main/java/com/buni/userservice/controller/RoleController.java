package com.buni.userservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.buniframework.util.Result;
import com.buni.userapi.log.SysLogRecord;
import com.buni.userservice.constant.CommonConstant;
import com.buni.userservice.service.RoleService;
import com.buni.userservice.vo.role.*;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @description 针对表【role(角色)】的数据库操作Controller
 * @createDate 2023-09-25 13:45:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class RoleController {

    private final RoleService roleService;


    /**
     * 新增角色
     *
     * @param addVO 权限信息
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "新增角色")
    @PostMapping("/role")
    public Result<Boolean> save(@RequestBody @Validated AddVO addVO) {
        return Result.ok(roleService.save(addVO));
    }


    /**
     * 修改角色
     *
     * @param updateVO 权限信息
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "修改角色")
    @PutMapping("/role")
    public Result<Boolean> update(@RequestBody @Validated UpdateVO updateVO) {
        return Result.ok(roleService.update(updateVO));
    }


    /**
     * 删除角色
     *
     * @param id 权限信息id
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "删除角色")
    @DeleteMapping("/role/{id:\\d+}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(roleService.delete(id));
    }


    /**
     * 批量删除权限
     *
     * @param batchIds 角色id集合
     * @return true/false
     */
    @SysLogRecord(description = CommonConstant.USER_MODEL + "批量删除角色")
    @DeleteMapping("/role")
    public Result<Boolean> BatchDelete(@RequestBody BatchIds batchIds) {
        return Result.ok(roleService.batchDelete(batchIds));
    }


    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @GetMapping("/role/{id:\\d+}")
    public Result<RoleInfoVO> findById(@PathVariable Long id) {
        return Result.ok(roleService.findById(id));
    }


    /**
     * 分页查询角色
     *
     * @param pageVO 查询条件
     * @return 角色信息
     */
    @GetMapping("/role")
    public Result<IPage<RoleGetVO>> page(PageVO pageVO) {
        return Result.ok(roleService.findPage(pageVO));
    }


}
