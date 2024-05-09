package com.buni.user.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.framework.config.executor.ExecutorConfig;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.user.dto.AuthDTO;
import com.buni.user.entity.Auth;
import com.buni.user.mapper.AuthMapper;
import com.buni.user.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【auth(用户鉴权)】的数据库操作Service实现
 * @createDate 2023-09-22 16:55:29
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {

    private RedisService redisService;


    /**
     * 保存用户登录鉴权信息
     *
     * @param authDTO 鉴权信息
     */
    @Async(value = ExecutorConfig.EXECUTOR_NAME)
    @Override
    public void saveOrUpdate(AuthDTO authDTO) {
        // 判断该平台是否已经有对应的鉴权信息
        Auth auth = super.getOne(Wrappers.<Auth>lambdaQuery().eq(Auth::getUserId, authDTO.getUserId()).eq(Auth::getClientIdentity, authDTO.getClientIdentity()));
        Auth newAuth = new Auth();
        if (ObjUtil.isEmpty(auth)) {
            BeanUtils.copyProperties(authDTO, newAuth);
            super.save(newAuth);
        }
        if (ObjUtil.isNotEmpty(auth)) {
            newAuth.setId(auth.getId());
            newAuth.setToken(authDTO.getToken());
            super.updateById(newAuth);
            // 移除redis中的旧token
            redisService.deleteKey(CommonConstant.TOKEN_REDIS_KEY + auth.getToken());
        }
    }


}