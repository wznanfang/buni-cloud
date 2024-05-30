package com.buni.user.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.buni.framework.config.exception.CustomException;
import com.buni.framework.enums.ResultEnum;
import com.buni.framework.util.CodeUtil;
import com.buni.framework.util.QRCodeUtil;
import com.buni.user.service.CommonService;
import com.buni.user.vo.common.QrCodeVO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zp.wei
 * @date 2024/5/30 15:21
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {


    /**
     * 获取验证码
     *
     * @return map
     */
    @Override
    public Map<String, Object> getCode() {
        // 第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = CodeUtil.createImage();
        // 将生成的验证码发送到前端
        String codes = (String) objs[0];
        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            log.error("验证码错误:{}", e.getMessage());
            throw new CustomException(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMessage());
        }
        byte[] bytes = out.toByteArray();
        Map<String, Object> map = new HashMap<>(2);
        map.put("codes", codes);
        map.put("image", bytes);
        return map;
    }


    /**
     * 生成二维码
     *
     * @param qrCodeVO 二维码内容
     * @param response response
     */
    @Override
    public void CreateQrCode(QrCodeVO qrCodeVO, HttpServletResponse response) {
        try (ServletOutputStream stream = response.getOutputStream()) {
            String logoPath = qrCodeVO.getLogoPath();
            if (ObjUtil.isEmpty(logoPath)) {
                QRCodeUtil.encode(qrCodeVO.getCode(), stream);
            } else {
                QRCodeUtil.encode(qrCodeVO.getCode(), logoPath, stream, true);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
