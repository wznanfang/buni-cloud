package com.buni.user.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.user.dto.auth.AuthDTO;
import com.buni.user.entity.Auth;
import com.buni.user.mapper.AuthMapper;
import com.buni.user.service.AuthService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【auth(用户鉴权)】的数据库操作Service实现
 * @createDate 2023-09-22 16:55:29
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {

    @Resource
    private RedisService redisService;


    /**
     * 保存用户登录鉴权信息
     *
     * @param authDTO 鉴权信息
     */
    @Async(value = CommonConstant.NORMAL_EXECUTOR_NAME)
    @Override
    public void saveOrUpdate(AuthDTO authDTO) {
        // 判断该平台是否已经有对应的鉴权信息
        Auth auth = super.getOne(Wrappers.<Auth>lambdaQuery().eq(Auth::getUserId, authDTO.getUserId()).eq(Auth::getClientIdentity, authDTO.getClientIdentity()));
        if (ObjUtil.isEmpty(auth)) {
            auth = new Auth();
            BeanUtils.copyProperties(authDTO, auth);
            super.save(auth);
            return;
        }
        String token = auth.getToken();
        auth.setToken(authDTO.getToken());
        super.updateById(auth);
        // 移除redis中的旧token
        redisService.deleteKey(CommonConstant.TOKEN_REDIS_KEY + token);
    }


    /**
     * 根据用户id查询鉴权信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> findByUserId(List<Long> userId) {
        return ObjUtil.isEmpty(userId) ? null : super.list(Wrappers.<Auth>lambdaQuery().in(Auth::getUserId, userId)).stream().map(Auth::getToken).toList();
    }


}