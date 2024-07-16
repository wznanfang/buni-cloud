package com.buni.user.service;

import com.buni.user.vo.common.QrCodeVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * @author zp.wei
 * @date 2024/5/30 15:21
 */
public interface CommonService {


    /**
     * 获取验证码
     *
     * @return
     */
    Map<String, Object> getCode();

    /**
     * 生成二维码
     *
     * @param qrCodeVO
     * @param response
     */
    void createQrCode(QrCodeVO qrCodeVO, HttpServletResponse response);

}
