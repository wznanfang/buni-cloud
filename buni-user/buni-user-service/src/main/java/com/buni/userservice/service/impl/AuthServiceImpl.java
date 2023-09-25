package com.buni.userservice.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.buniframework.config.executor.ExecutorConfig;
import com.buni.buniframework.config.redis.RedisService;
import com.buni.buniframework.constant.CommonConstant;
import com.buni.usercommon.dto.AuthDTO;
import com.buni.usercommon.entity.Auth;
import com.buni.usercommon.entity.User;
import com.buni.userservice.mapper.AuthMapper;
import com.buni.userservice.service.AuthService;
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
     * @param authDTO
     * @return
     */
    @Async(value = ExecutorConfig.EXECUTOR_NAME)
    @Override
    public void saveOrUpdate(AuthDTO authDTO) {
        //判断该平台是否已经有对应的鉴权信息
        Auth auth = super.getOne(Wrappers.<Auth>lambdaQuery().eq(Auth::getUserId, authDTO.getUserId()).eq(Auth::getClientIdentity, authDTO.getClientIdentity()));
        Auth newAuth = new Auth();
        if (ObjUtil.isNotEmpty(auth)) {
            newAuth.setId(auth.getId());
            newAuth.setToken(authDTO.getToken());
            super.updateById(newAuth);
            //移除redis中的旧token
            redisService.deleteKey(CommonConstant.TOKEN_REDIS_KEY + auth.getToken());
        } else {
            BeanUtils.copyProperties(authDTO, newAuth);
            super.save(newAuth);
        }
    }


}