package com.buni.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.SmUtil;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.framework.util.HeaderUtil;
import com.buni.user.constant.UserConstant;
import com.buni.user.dto.AuthDTO;
import com.buni.user.entity.Authority;
import com.buni.user.entity.User;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.enums.ErrorEnum;
import com.buni.user.service.*;
import com.buni.user.util.TokenUtil;
import com.buni.user.vo.login.AuthorityVO;
import com.buni.user.vo.login.LoginVO;
import com.buni.user.vo.login.TokenVO;
import com.buni.user.vo.login.UserLoginVO;
import com.buni.user.vo.role.AuthorityDTO;
import com.buni.user.vo.role.RoleAuthorityDTO;
import com.buni.user.vo.role.UserRoleDTO;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserConstant userConstant;
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
        // 查询用户的角色权限
        List<AuthorityVO> authorityVoList = getUserRole(user.getId());
        userLoginVO.setAuthorityVOS(authorityVoList);
        redisService.setOneHour(CommonConstant.TOKEN_REDIS_KEY + tokenVO.getToken(), userLoginVO);
        // 记录用户鉴权信息
        AuthDTO authDTO = AuthDTO.builder().userId(user.getId()).clientIdentity(HeaderUtil.getIdentity()).token(tokenVO.getToken()).build();
        authService.saveOrUpdate(authDTO);
        return userLoginVO;
    }

    private List<AuthorityVO> getUserRole(Long userId) {
        List<UserRoleDTO> userRoles = userRoleService.findByUserId(userId);
        List<AuthorityVO> authorityVoList = new ArrayList<>();
        if (CollUtil.isNotEmpty(userRoles)) {
            List<Long> roleIds = userRoles.stream().map(UserRoleDTO::getRoleId).toList();
            List<RoleAuthorityDTO> roleAuthList = roleAuthorityService.findByRoleIds(roleIds);
            List<Long> authorityIds = roleAuthList.stream().map(RoleAuthorityDTO::getAuthorityId).toList();
            List<AuthorityDTO> authorityList = authorityService.findByIds(authorityIds);
            authorityVoList = authorityList.stream().map(authorityDTO -> AuthorityVO.builder().type(authorityDTO.getType()).code(authorityDTO.getCode()).build()).toList();
            redisService.setOneHour(Authority.REDIS_KEY + userId, authorityList);
        }
        return authorityVoList;
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
