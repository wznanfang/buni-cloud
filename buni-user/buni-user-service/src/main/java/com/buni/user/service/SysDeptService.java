package com.buni.user.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.user.entity.SysDept;
import com.buni.user.vo.IdVOs;
import com.buni.user.vo.dept.*;

import java.util.List;

/**
 * @author buni
 * @description 针对表【sys_dept(部门)】的数据库操作Service
 * @createDate 2025-02-11 10:16:51
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 新增部门
     *
     * @param addVO 部门信息
     * @return true/false
     */
    boolean save(AddVO addVO);

    /**
     * 根据ID修改部门
     *
     * @param updateVO
     * @return true/false
     */
    boolean update(UpdateVO updateVO);

    /**
     * 根据ID删除部门
     *
     * @param id id
     * @return true/false
     */
    boolean delete(Long id);

    /**
     * 根据ID集合批量删除部门
     *
     * @param idVOs id集合
     * @return true/false
     */
    boolean batchDelete(IdVOs idVOs);

    /**
     * 根据ID查询部门
     *
     * @param id
     * @return {@link DeptInfoVO}
     */
    DeptInfoVO findById(Long id);

    /**
     * 分页查询用户信息
     *
     * @param pageVO 分页条件
     * @return IPage<DeptGetVO>
     */
    IPage<DeptGetVO> findPage(PageVO pageVO);

    /**
     * 获取菜单树
     *
     * @return
     */
    List<Tree<String>> findMenuTree();

    /**
     * 根据父级id查询子集部门
     *
     * @param id
     * @return
     */
    List<DeptGetVO> findByParentId(Long id);


}
