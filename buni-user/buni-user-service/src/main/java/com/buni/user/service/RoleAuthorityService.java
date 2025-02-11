package com.buni.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.user.entity.SysRoleAuthority;
import com.buni.user.dto.role.RoleAuthorityDTO;
import com.buni.user.vo.roleauthority.AddVO;
import com.buni.user.vo.roleauthority.UpdateVO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【role_authority(角色权限)】的数据库操作Service
 * @createDate 2023-09-25 13:45:26
 */
public interface RoleAuthorityService extends IService<SysRoleAuthority> {


    /**
     * 保存
     *
     * @param addVO
     * @return
     */
    boolean save(AddVO addVO);

    /**
     * 修改
     *
     * @param updateVO
     * @return
     */
    boolean update(UpdateVO updateVO);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    List<Long> findById(Long id);

    /**
     * 查询roleAuthority
     *
     * @param roleIds
     * @return {@link List}<{@link RoleAuthorityDTO}>
     */
    List<RoleAuthorityDTO> findByRoleIds(List<Long> roleIds);

    /**
     * 查询roleAuthority
     *
     * @param authorityId 权限id
     * @return {@link List}<{@link RoleAuthorityDTO}>
     */
    List<RoleAuthorityDTO> findByAuthorityId(Long authorityId);

    /**
     * 根据角色id删除角色权限
     *
     * @param roleIds 角色id集合
     */
    void deleteByRoleIds(List<Long> roleIds);


}
