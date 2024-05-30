package com.buni.user.service;

import com.buni.user.vo.common.QrCodeVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * @author zp.wei
 * @date 2024/5/30 15:21
 */
public interface CommonService {

    Map<String, Object> getCode();

    void CreateQrCode(QrCodeVO qrCodeVO, HttpServletResponse response);

}
