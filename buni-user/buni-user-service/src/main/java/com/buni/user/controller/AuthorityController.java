package com.buni.user.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.framework.util.Result;
import com.buni.user.constant.CommonConstant;
import com.buni.user.dto.role.AuthorityDTO;
import com.buni.user.enums.AuthTypeEnum;
import com.buni.user.log.SysLogRecord;
import com.buni.user.service.AuthorityService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.authority.*;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【authority(权限)】的数据库操作Controller
 * @createDate 2023-09-25 13:45:26
 */
@Tag(name = "权限管理")
@ApiSort(4)
@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class AuthorityController {

    @Resource
    private AuthorityService authorityService;


    /**
     * 新增权限
     *
     * @param addVO 权限信息
     * @return true/false
     */
    @Operation(summary = "新增权限")
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
    @Operation(summary = "修改权限")
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
    @Operation(summary = "删除权限")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "删除权限")
    @DeleteMapping("/authority/{id:\\d+}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(authorityService.delete(id));
    }


    /**
     * 批量删除权限
     *
     * @param idVOs 权限信息id
     * @return true/false
     */
    @Operation(summary = "批量删除权限")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "批量删除权限")
    @DeleteMapping("/authority")
    public Result<Boolean> BatchDelete(@RequestBody @Validated IdVOs idVOs) {
        return Result.ok(authorityService.batchDelete(idVOs));
    }


    /**
     * 根据id查询权限
     *
     * @param id id
     * @return 权限信息
     */
    @Operation(summary = "根据id查询权限")
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
    @Operation(summary = "分页查询")
    @GetMapping("/authority")
    public Result<IPage<AuthorityGetVO>> page(@ParameterObject PageVO pageVO) {
        return Result.ok(authorityService.findPage(pageVO));
    }


    /**
     * 菜单树结构列表
     *
     * @return 菜单树结构列表
     */
    @Operation(summary = "菜单树结构列表", description = "返回树结构菜单")
    @GetMapping("findMenuTree")
    public Result<List<Tree<String>>> findMenuTree() {
        return Result.ok(authorityService.findMenuTree());
    }


    /**
     * 根据父级id查询子集权限
     *
     * @param id
     * @return
     */
    @Operation(summary = "根据父级id查询子集权限")
    @GetMapping("/authority/findByParent/{id}")
    public Result<List<AuthorityGetVO>> findByParentId(@PathVariable("id") Long id) {
        return Result.ok(authorityService.findByParentId(id));
    }


}
