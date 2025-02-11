package com.buni.user.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.buni.framework.util.Result;
import com.buni.user.constant.CommonConstant;
import com.buni.user.log.SysLogRecord;
import com.buni.user.service.SysDeptService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.dept.*;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "部门管理")
@ApiSort(4)
@RestController
@AllArgsConstructor
@RequestMapping("/v1/dept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;


    /**
     * 新增部门
     *
     * @param addVO 部门信息
     * @return true/false
     */
    @Operation(summary = "新增部门")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "新增部门")
    @PostMapping()
    public Result<Boolean> save(@RequestBody @Validated AddVO addVO) {
        return Result.ok(sysDeptService.save(addVO));
    }


    /**
     * 修改部门
     *
     * @param updateVO 部门信息
     * @return true/false
     */
    @Operation(summary = "修改部门")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "修改部门")
    @PutMapping()
    public Result<Boolean> update(@RequestBody @Validated UpdateVO updateVO) {
        return Result.ok(sysDeptService.update(updateVO));
    }


    /**
     * 删除部门
     *
     * @param id 部门信息id
     * @return true/false
     */
    @Operation(summary = "删除部门")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "删除部门")
    @DeleteMapping("/{id:\\d+}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(sysDeptService.delete(id));
    }


    /**
     * 批量删除部门
     *
     * @param idVOs 部门信息id
     * @return true/false
     */
    @Operation(summary = "批量删除部门")
    @SysLogRecord(description = CommonConstant.USER_MODEL + "批量删除部门")
    @DeleteMapping()
    public Result<Boolean> BatchDelete(@RequestBody @Validated IdVOs idVOs) {
        return Result.ok(sysDeptService.batchDelete(idVOs));
    }


    /**
     * 根据id查询部门
     *
     * @param id id
     * @return 部门信息
     */
    @Operation(summary = "根据id查询部门")
    @GetMapping("/{id:\\d+}")
    public Result<DeptInfoVO> findById(@PathVariable Long id) {
        return Result.ok(sysDeptService.findById(id));
    }


    /**
     * 分页查询
     *
     * @param pageVO 查询条件
     * @return 部门信息
     */
    @Operation(summary = "分页查询")
    @GetMapping()
    public Result<IPage<DeptGetVO>> page(@ParameterObject PageVO pageVO) {
        return Result.ok(sysDeptService.findPage(pageVO));
    }


    /**
     * 菜单树结构列表
     *
     * @return 菜单树结构列表
     */
    @Operation(summary = "菜单树结构列表", description = "返回树结构菜单")
    @GetMapping("/findMenuTree")
    public Result<List<Tree<String>>> findMenuTree() {
        return Result.ok(sysDeptService.findMenuTree());
    }


    /**
     * 根据父级id查询子集部门
     *
     * @param id
     * @return
     */
    @Operation(summary = "根据父级id查询子集部门")
    @GetMapping("/findByParent/{id}")
    public Result<List<DeptGetVO>> findByParentId(@PathVariable("id") Long id) {
        return Result.ok(sysDeptService.findByParentId(id));
    }


}
