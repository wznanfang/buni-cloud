package com.buni.userservice.service;

import com.buni.usercommon.vo.login.LoginVO;
import com.buni.usercommon.vo.login.UserLoginVO;

public interface LoginService {

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


}
