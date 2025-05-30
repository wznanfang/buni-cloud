package com.buni.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.framework.util.Result;
import com.buni.user.constant.CommonConstant;
import com.buni.user.log.SysLogRecord;
import com.buni.user.service.SysRoleService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.role.*;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @description 针对表【role(角色)】的数据库操作Controller
 * @createDate 2023-09-25 13:45:26
 */
@Tag(name = "角色管理")
@ApiSort(3)
@RestController
@AllArgsConstructor
@RequestMapping("/v1/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;


    /**
     * 新增角色
     *
     * @param addVO 角色信息
     * @return true/false
     */
    @Operation(summary = "新增角色")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "新增角色")
    @PostMapping()
    public Result<Boolean> save(@RequestBody @Validated AddVO addVO) {
        return Result.ok(sysRoleService.save(addVO));
    }


    /**
     * 修改角色
     *
     * @param updateVO 角色信息
     * @return true/false
     */
    @Operation(summary = "修改角色")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "修改角色")
    @PutMapping()
    public Result<Boolean> update(@RequestBody @Validated UpdateVO updateVO) {
        return Result.ok(sysRoleService.update(updateVO));
    }


    /**
     * 删除角色
     *
     * @param id 角色信息id
     * @return true/false
     */
    @Operation(summary = "删除角色")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "删除角色")
    @DeleteMapping("/{id:\\d+}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(sysRoleService.delete(id));
    }


    /**
     * 批量删除权限
     *
     * @param idVOs 角色id集合
     * @return true/false
     */
    @Operation(summary = "批量删除角色")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "批量删除角色")
    @DeleteMapping()
    public Result<Boolean> BatchDelete(@RequestBody @Validated IdVOs idVOs) {
        return Result.ok(sysRoleService.batchDelete(idVOs));
    }


    /**
     * 根据id查询角色
     *
     * @param id 角色id
     * @return 角色信息
     */
    @Operation(summary = "根据id查询角色")
    @GetMapping("/{id:\\d+}")
    public Result<RoleInfoVO> findById(@PathVariable Long id) {
        return Result.ok(sysRoleService.findById(id));
    }


    /**
     * 分页查询角色
     *
     * @param pageVO 查询条件
     * @return 角色信息
     */
    @Operation(summary = "分页查询角色")
    @GetMapping()
    public Result<IPage<RoleGetVO>> page(@ParameterObject PageVO pageVO) {
        return Result.ok(sysRoleService.findPage(pageVO));
    }


}
