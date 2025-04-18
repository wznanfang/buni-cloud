package com.buni.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.buni.user.dto.auth.AuthDTO;
import com.buni.user.entity.SysAuth;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【auth(用户鉴权)】的数据库操作Service
 * @createDate 2023-09-22 16:55:29
 */
public interface SysAuthService extends IService<SysAuth> {


    /**
     * 保存用户登录鉴权信息
     *
     * @param authDTO
     * @return
     */
    void saveOrUpdate(AuthDTO authDTO);

    /**
     * 根据用户id查询用户登录鉴权信息
     *
     * @param userId
     * @return
     */
    List<String> findByUserId(List<Long> userId);


}
