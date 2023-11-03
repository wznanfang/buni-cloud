package com.buni.userservice.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.usercommon.entity.Authority;
import com.buni.usercommon.vo.role.AuthorityDTO;
import com.buni.userservice.vo.authority.*;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【authority(角色)】的数据库操作Service
 * @createDate 2023-09-25 13:45:26
 */
public interface AuthorityService extends IService<Authority> {


    /**
     * 新增权限
     *
     * @param addVO 权限信息
     * @return true/false
     */
    boolean save(AddVO addVO);

    /**
     * 根据ID修改权限
     *
     * @param updateVO
     * @return true/false
     */
    boolean update(UpdateVO updateVO);

    /**
     * 根据ID删除权限
     *
     * @param id
     * @return true/false
     */
    boolean delete(Long id);

    /**
     * 根据ID查询权限
     *
     * @param id
     * @return {@link AuthorityInfoVO}
     */
    AuthorityInfoVO findById(Long id);

    /**
     * 分页查询用户信息
     *
     * @param pageVO 分页条件
     * @return IPage<UserInfoVO>
     */
    IPage<AuthorityGetVO> findPage(PageVO pageVO);

    /**
     * 根据id查询权限
     *
     * @param ids
     * @return {@link List}<{@link AuthorityDTO}>
     */
    List<AuthorityDTO> findByIds(List<Long> ids);


}