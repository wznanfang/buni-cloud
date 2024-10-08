package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.SmUtil;
import com.buni.file.FileDubboService;
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
import com.buni.user.entity.Authority;
import com.buni.user.entity.User;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.properties.UserProperties;
import com.buni.user.service.*;
import com.buni.user.util.TokenUtil;
import com.buni.user.vo.login.LoginVO;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserProperties userProperties;
    @Resource
    private RedisService redisService;
    @Resource
    private AuthService authService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleAuthorityService roleAuthorityService;
    @Resource
    private AuthorityService authorityService;
    @Resource
    private UserService userService;
    @DubboReference
    private FileDubboService fileDubboService;


    /**
     * 登录
     *
     * @param loginVO 登录信息
     * @return {@link UserLoginVO}
     */
    @Override
    public UserLoginVO login(LoginVO loginVO) {
        User user = userService.findByUsername(loginVO.getUsername());
        String password = EncryptUtil.decrypt(loginVO.getPassword());
        if (ObjUtil.isEmpty(user) || user.getDeleted().equals(BooleanEnum.YES) || !SmUtil.sm3(userProperties.getSalt() + password).equals(user.getPassword())) {
            throw new CustomException(ErrorEnum.USER_PASSWORD_ERROR.getCode(), ErrorEnum.USER_PASSWORD_ERROR.getMessage());
        }
        if (user.getEnable().equals(BooleanEnum.NO)) {
            throw new CustomException(ErrorEnum.USER_FORBIDDEN.getCode(), ErrorEnum.USER_FORBIDDEN.getMessage());
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        // 获取token
        TokenVO tokenVO = TokenUtil.getToken();
        userLoginVO.setTokenVO(tokenVO);
        redisService.setOneHour(CommonConstant.TOKEN_REDIS_KEY + tokenVO.getToken(), userLoginVO);
        // 查询用户的角色权限
        if (!user.getAdmin().equals(BooleanEnum.YES)) {
            getUserRole(user.getId());
        }
        // 记录用户鉴权信息
        AuthDTO authDTO = AuthDTO.builder().userId(user.getId()).clientIdentity(HeaderUtil.getIdentity()).token(tokenVO.getToken()).build();
        authService.saveOrUpdate(authDTO);
        return userLoginVO;
    }

    private void getUserRole(Long userId) {
        List<UserRoleDTO> userRoles = userRoleService.findByUserId(userId);
        if (CollUtil.isNotEmpty(userRoles)) {
            List<Long> roleIds = userRoles.stream().map(UserRoleDTO::getRoleId).toList();
            List<RoleAuthorityDTO> roleAuthList = roleAuthorityService.findByRoleIds(roleIds);
            List<Long> authorityIds = roleAuthList.stream().map(RoleAuthorityDTO::getAuthorityId).toList();
            List<AuthorityDTO> authorityList = authorityService.findByIds(authorityIds);
            redisService.setOneHour(Authority.REDIS_KEY + userId, authorityList);
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
        redisService.deleteKey(Authority.REDIS_KEY + userId);
        return true;
    }


}
