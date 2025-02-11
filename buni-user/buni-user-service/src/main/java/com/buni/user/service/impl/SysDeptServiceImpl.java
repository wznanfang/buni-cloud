package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.user.entity.SysAuthority;
import com.buni.user.entity.SysDept;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.mapper.SysDeptMapper;
import com.buni.user.service.SysDeptService;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.dept.*;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author buni
 * @description 针对表【sys_dept(部门)】的数据库操作Service实现
 * @createDate 2025-02-11 10:16:51
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Resource
    private RedisService redisService;


    /**
     * 新增部门
     *
     * @param addVO 权限信息
     * @return
     */
    @Override
    public boolean save(AddVO addVO) {
        SysDept exist = super.getOne(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getName, addVO.getName()));
        if (ObjUtil.isNotEmpty(exist)) {
            throw new CustomException(ErrorEnum.DEPT_EXISTS.getCode(), ErrorEnum.DEPT_EXISTS.getMessage());
        }
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(addVO, sysDept);
        super.save(sysDept);
        return true;
    }


    /**
     * 更新部门
     *
     * @param updateVO
     * @return
     */
    @Override
    public boolean update(UpdateVO updateVO) {
        SysDept exist = super.getOne(Wrappers.<SysDept>lambdaQuery().ne(SysDept::getId, updateVO.getId()));
        if (ObjUtil.isNotEmpty(exist) && !updateVO.getId().equals(exist.getId())) {
            throw new CustomException(ErrorEnum.AUTHORITY_EXISTS.getCode(), ErrorEnum.AUTHORITY_EXISTS.getMessage());
        }
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(updateVO, sysDept);
        super.updateById(sysDept);
        redisService.deleteKey(SysDept.REDIS_KEY + updateVO.getId());
        return true;
    }


    /**
     * 删除部门
     *
     * @param id id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        SysDept sysDept = getAuthority(id);
        super.removeById(sysDept);
        redisService.deleteKey(SysAuthority.REDIS_KEY + id);
        return true;
    }

    private SysDept getAuthority(Long id) {
        SysDept sysDept = super.getById(id);
        if (ObjUtil.isEmpty(sysDept)) {
            throw new CustomException(ErrorEnum.DEPT_NOT_EXISTS.getCode(), ErrorEnum.DEPT_NOT_EXISTS.getMessage());
        }
        return sysDept;
    }


    /**
     * 批量删除部门
     *
     * @param idVOs id集合
     * @return
     */
    @Override
    public boolean batchDelete(IdVOs idVOs) {
        List<Long> ids = idVOs.getIds();
        super.removeBatchByIds(ids);
        List<String> keys = new ArrayList<>();
        ids.forEach(id -> keys.add(SysAuthority.REDIS_KEY + id));
        redisService.delAllByKeys(keys);
        return true;
    }


    /**
     * 根据id查询部门信息
     *
     * @param id
     * @return
     */
    @Override
    public DeptInfoVO findById(Long id) {
        DeptInfoVO infoVO = (DeptInfoVO) redisService.get(SysDept.REDIS_KEY + id);
        if (ObjUtil.isEmpty(infoVO)) {
            SysDept sysDept = getAuthority(id);
            infoVO = new DeptInfoVO();
            BeanUtils.copyProperties(sysDept, infoVO);
            redisService.setOneHour(SysAuthority.REDIS_KEY + id, infoVO);
        }
        return infoVO;
    }


    /**
     * 分页查询部门信息
     *
     * @param pageVO 分页条件
     * @return
     */
    @Override
    public IPage<DeptGetVO> findPage(PageVO pageVO) {
        IPage<SysDept> ipage = new Page<>(pageVO.getCurrent(), pageVO.getSize());
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ObjUtil.isNotEmpty(pageVO.getParentId()), SysDept::getParentId, pageVO.getParentId());
        queryWrapper.lambda().like(ObjUtil.isNotEmpty(pageVO.getLeaderUserId()), SysDept::getLeaderUserId, pageVO.getLeaderUserId());
        queryWrapper.lambda().like(ObjUtil.isNotEmpty(pageVO.getName()), SysDept::getName, pageVO.getName());
        queryWrapper.lambda().orderByAsc(SysDept::getId);
        IPage<SysDept> infoPage = super.page(ipage, queryWrapper);
        IPage<DeptGetVO> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        List<DeptGetVO> list = Optional.ofNullable(infoPage.getRecords()).orElse(new ArrayList<>()).stream().map(dept -> {
            DeptGetVO getVO = new DeptGetVO();
            BeanUtils.copyProperties(dept, getVO);
            return getVO;
        }).toList();
        resultPage.setRecords(list);
        return resultPage;
    }


    /**
     * 查询部门树形结构
     *
     * @return
     */
    @Override
    public List<Tree<String>> findMenuTree() {
        List<SysDept> list = super.list();
        List<Tree<String>> treeNodes = new ArrayList<>();
        if (ObjUtil.isNotEmpty(list)) {
            treeNodes = TreeUtil.build(list, String.valueOf(CommonConstant.ZERO), (treeNode, tree) -> {
                tree.setId(String.valueOf(treeNode.getId()));
                tree.setParentId(String.valueOf(treeNode.getParentId()));
                tree.setName(treeNode.getName());
                tree.putExtra("name", treeNode.getName());
                tree.putExtra("leaderUserId", treeNode.getLeaderUserId());
            });
        }
        return treeNodes;
    }


    /**
     * 根据父id查询子部门信息
     *
     * @param id
     * @return
     */
    @Override
    public List<DeptGetVO> findByParentId(Long id) {
        List<SysDept> list = super.list(Wrappers.<SysDept>lambdaQuery().eq(SysDept::getParentId, id));
        List<DeptGetVO> getVos = new ArrayList<>();
        if (CollUtil.isNotEmpty(list)) {
            list.forEach(dept -> {
                DeptGetVO getVO = new DeptGetVO();
                BeanUtils.copyProperties(dept, getVO);
                getVos.add(getVO);
            });
        }
        return getVos;
    }


}




