package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.SmUtil;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.framework.util.EncryptUtil;
import com.buni.framework.util.HeaderUtil;
import com.buni.user.dto.auth.AuthDTO;
import com.buni.user.dto.login.TokenVO;
import com.buni.user.dto.login.UserLoginVO;
import com.buni.user.dto.role.AuthorityDTO;
import com.buni.user.dto.role.RoleAuthorityDTO;
import com.buni.user.dto.role.UserRoleDTO;
import com.buni.user.entity.SysAuthority;
import com.buni.user.entity.SysUser;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.properties.UserProperties;
import com.buni.user.service.*;
import com.buni.user.util.TokenUtil;
import com.buni.user.vo.login.LoginVO;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SysSysLoginServiceImpl implements SysLoginService {

    @Resource
    private UserProperties userProperties;
    @Resource
    private RedisService redisService;
    @Resource
    private SysAuthService sysAuthService;
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleAuthorityService sysRoleAuthorityService;
    @Resource
    private SysAuthorityService sysAuthorityService;
    @Resource
    private SysUserService sysUserService;


    /**
     * 登录
     *
     * @param loginVO 登录信息
     * @return {@link UserLoginVO}
     */
    @Override
    public UserLoginVO login(LoginVO loginVO) {
        SysUser sysUser = sysUserService.findByUsername(loginVO.getUsername());
        String password = EncryptUtil.decrypt(loginVO.getPassword());
        if (ObjUtil.isEmpty(sysUser) || sysUser.getDeleted().equals(BooleanEnum.YES) || !SmUtil.sm3(userProperties.getSalt() + password).equals(sysUser.getPassword())) {
            throw new CustomException(ErrorEnum.USER_PASSWORD_ERROR.getCode(), ErrorEnum.USER_PASSWORD_ERROR.getMessage());
        }
        if (sysUser.getEnable().equals(BooleanEnum.NO)) {
            throw new CustomException(ErrorEnum.USER_FORBIDDEN.getCode(), ErrorEnum.USER_FORBIDDEN.getMessage());
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(sysUser, userLoginVO);
        // 获取token
        TokenVO tokenVO = TokenUtil.getToken();
        userLoginVO.setTokenVO(tokenVO);
        redisService.setOneHour(CommonConstant.TOKEN_REDIS_KEY + tokenVO.getToken(), userLoginVO);
        // 查询用户的角色权限
        if (!sysUser.getAdmin().equals(BooleanEnum.YES)) {
            getUserRole(sysUser.getId());
        }
        // 记录用户鉴权信息
        AuthDTO authDTO = AuthDTO.builder().userId(sysUser.getId()).clientIdentity(HeaderUtil.getIdentity()).token(tokenVO.getToken()).build();
        sysAuthService.saveOrUpdate(authDTO);
        return userLoginVO;
    }

    private void getUserRole(Long userId) {
        List<UserRoleDTO> userRoles = sysUserRoleService.findByUserId(userId);
        if (CollUtil.isNotEmpty(userRoles)) {
            List<Long> roleIds = userRoles.stream().map(UserRoleDTO::getRoleId).toList();
            List<RoleAuthorityDTO> roleAuthList = sysRoleAuthorityService.findByRoleIds(roleIds);
            List<Long> authorityIds = roleAuthList.stream().map(RoleAuthorityDTO::getAuthorityId).toList();
            List<AuthorityDTO> authorityList = sysAuthorityService.findByIds(authorityIds);
            redisService.listRightPushAll(SysAuthority.REDIS_KEY + userId, authorityList);
        }
    }


    /**
     * 退出登录
     *
     * @return true/false
     */
    @Override
    public Boolean loginOut() {
        Long userId = HeaderUtil.getUserId();
        redisService.deleteKey(CommonConstant.TOKEN_REDIS_KEY + HeaderUtil.getToken());
        redisService.deleteKey(SysAuthority.REDIS_KEY + userId);
        return true;
    }


}
