package com.buni.user.controller;

import com.buni.framework.util.Result;
import com.buni.user.service.SysRoleAuthorityService;
import com.buni.user.vo.roleauthority.AddVO;
import com.buni.user.vo.roleauthority.UpdateVO;
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
@Tag(name = "角色权限管理")
@ApiSort(4)
@RestController
@AllArgsConstructor
@RequestMapping("/v1/roleAuthority")
public class SysRoleAuthorityController {

    @Resource
    private SysRoleAuthorityService sysRoleAuthorityService;


    @Operation(summary = "新增角色权限")
    @PostMapping()
    public Result<Boolean> save(@RequestBody AddVO addVO) {
        return Result.ok(sysRoleAuthorityService.save(addVO));
    }


    @Operation(summary = "编辑角色权限")
    @PutMapping()
    public Result<Boolean> update(@RequestBody UpdateVO updateVO) {
        return Result.ok(sysRoleAuthorityService.update(updateVO));
    }


    @Operation(summary = "查询角色权限")
    @GetMapping("/{id:\\d+}")
    public Result<List<Long>> findById(@PathVariable Long id) {
        return Result.ok(sysRoleAuthorityService.findById(id));
    }


}
