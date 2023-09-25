package com.buni.userservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.usercommon.entity.Authority;
import com.buni.usercommon.vo.role.AuthorityDTO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【authority(角色)】的数据库操作Service
 * @createDate 2023-09-25 13:45:26
 */
public interface AuthorityService extends IService<Authority> {


    /**
     * 根据id查询权限
     *
     * @param ids
     * @return {@link List}<{@link AuthorityDTO}>
     */
    List<AuthorityDTO> findByIds(List<Long> ids);


}