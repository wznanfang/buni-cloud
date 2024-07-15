package com.buni.user.controller;

import com.buni.framework.util.Result;
import com.buni.user.service.UserRoleService;
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
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;


    @Operation(summary = "新增用户角色")
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody AddVO addVO) {
        return Result.ok(userRoleService.save(addVO));
    }


    @Operation(summary = "编辑用户角色")
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody UpdateVO updateVO) {
        return Result.ok(userRoleService.update(updateVO));
    }


    @Operation(summary = "查询用户角色")
    @GetMapping("findById/{id}")
    public Result<List<Long>> findById(@PathVariable Long id) {
        return Result.ok(userRoleService.findById(id));
    }


}
