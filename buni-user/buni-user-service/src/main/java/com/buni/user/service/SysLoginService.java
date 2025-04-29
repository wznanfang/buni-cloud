package com.buni.user.service;

import com.buni.user.vo.login.LoginVO;
import com.buni.user.dto.login.UserLoginVO;
import org.springframework.web.bind.annotation.RequestParam;

public interface SysLoginService {

    /**
     * 登录
     *
     * @param loginVO 登录信息
     * @return 用户信息
     */
    UserLoginVO login(LoginVO loginVO);

    /**
     * 退出登录
     *
     * @return true/false
     */
    Boolean loginOut();

    /**
     * 微信登录
     *
     * @param code
     * @return
     */
    UserLoginVO wxLogin(String code);


}
