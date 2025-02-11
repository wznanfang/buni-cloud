package com.buni.user.controller;

import com.buni.framework.util.Result;
import com.buni.user.service.SysUserRoleService;
import com.buni.user.vo.userrole.AddVO;
import com.buni.user.vo.userrole.UpdateVO;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zp.wei
 * @date 2024/7/15 14:37
 */
@Tag(name = "用户角色管理")
@ApiSort(4)
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;


    @Operation(summary = "新增用户角色")
    @PostMapping("userRole")
    public Result<Boolean> save(@RequestBody AddVO addVO) {
        return Result.ok(sysUserRoleService.save(addVO));
    }


    @Operation(summary = "编辑用户角色")
    @PutMapping("userRole")
    public Result<Boolean> update(@RequestBody UpdateVO updateVO) {
        return Result.ok(sysUserRoleService.update(updateVO));
    }


    @Operation(summary = "查询用户角色")
    @GetMapping("userRole/{id}")
    public Result<List<Long>> findById(@PathVariable Long id) {
        return Result.ok(sysUserRoleService.findById(id));
    }


}
