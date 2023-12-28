package com.buni.userservice.util;

import cn.hutool.core.util.RandomUtil;
import com.buni.buniframework.constant.CommonConstant;
import com.buni.usercommon.vo.login.TokenVO;

import java.io.Serializable;

/**
 * @author zp.wei
 * @date 2023/12/28 9:26
 */
public class TokenUtil implements Serializable {


	/**
	 * 获取token
	 *
	 * @return token
	 */
	public static TokenVO getToken() {
		String token = RandomUtil.randomString(32);
		TokenVO tokenVO = new TokenVO();
		tokenVO.setExpireTime(System.currentTimeMillis() + CommonConstant.EXPIRE_TIME_MS);
		tokenVO.setToken(token);
		tokenVO.setPrefix(CommonConstant.PREFIX);
		return tokenVO;
	}


}
