package com.buni.userservice.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.SmUtil;
import com.buni.buniframework.config.exception.CustomException;
import com.buni.buniframework.config.redis.RedisService;
import com.buni.buniframework.constant.CommonConstant;
import com.buni.buniframework.util.HeaderUtil;
import com.buni.usercommon.dto.AuthDTO;
import com.buni.usercommon.entity.Authority;
import com.buni.usercommon.entity.User;
import com.buni.usercommon.enums.BooleanEnum;
import com.buni.usercommon.enums.ErrorEnum;
import com.buni.usercommon.vo.login.LoginVO;
import com.buni.usercommon.vo.login.TokenVO;
import com.buni.usercommon.vo.login.UserLoginVO;
import com.buni.usercommon.vo.role.AuthorityDTO;
import com.buni.usercommon.vo.role.RoleAuthorityDTO;
import com.buni.usercommon.vo.role.UserRoleDTO;
import com.buni.userservice.constant.UserConstant;
import com.buni.userservice.service.*;
import com.buni.userservice.util.TokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {


    private UserConstant userConstant;
    private RedisService redisService;
    private AuthService authService;
    private UserRoleService userRoleService;
    private RoleAuthorityService roleAuthorityService;
    private AuthorityService authorityService;
    private UserService userService;


    /**
     * 登录
     *
     * @param loginVO 登录信息
     * @return {@link UserLoginVO}
     */
    @Override
    public UserLoginVO login(LoginVO loginVO) {
        User user = userService.findByUsername(loginVO.getUsername());
        if (ObjUtil.isEmpty(user) || user.getDeleted().equals(BooleanEnum.YES)) {
            throw new CustomException(ErrorEnum.USER_NOT_EXISTS.getCode(), ErrorEnum.USER_NOT_EXISTS.getMessage());
        }
        if (user.getEnable().equals(BooleanEnum.NO)) {
            throw new CustomException(ErrorEnum.USER_FORBIDDEN.getCode(), ErrorEnum.USER_FORBIDDEN.getMessage());
        }
        if (!SmUtil.sm3(userConstant.getSalt() + loginVO.getPassword()).equals(user.getPassword())) {
            throw new CustomException(ErrorEnum.USER_PASSWORD_ERROR.getCode(), ErrorEnum.USER_PASSWORD_ERROR.getMessage());
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        // 获取token
        TokenVO tokenVO = TokenUtil.getToken();
        userLoginVO.setTokenVO(tokenVO);
        redisService.setOneHour(CommonConstant.TOKEN_REDIS_KEY + tokenVO.getToken(), userLoginVO);
        // 查询用户的角色权限
        getUserRole(user.getId());
        // 记录到用户鉴权信息
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
        String userId = HeaderUtil.getUserId();
        redisService.deleteKey(CommonConstant.TOKEN_REDIS_KEY + HeaderUtil.getToken());
        redisService.deleteKey(Authority.REDIS_KEY + userId);
        return true;
    }


}
