package com.buni.buniframework.util;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.buni.buniframework.config.redis.RedisService;
import com.buni.buniframework.constant.CommonConstant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 获取用户的信息
 *
 * @author zp.wei
 * @date 2023/9/22 11:59
 */
@Data
@AllArgsConstructor
public class HeaderUtil {

    private RedisService redisService;


    /**
     * 获取请求体
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }


    /**
     * 获取用户token
     *
     * @return {@link String}
     */
    public static String getToken() {
        HttpServletRequest request = getRequest();
        return StrUtil.subAfter(request.getHeader(CommonConstant.AUTHORIZATION), CommonConstant.PREFIX, true);
    }


    /**
     * 获取用户id
     *
     * @return {@link String}
     */
    public static String getUserId() {
        HttpServletRequest request = getRequest();
        return ObjUtil.isEmpty(request.getHeader(CommonConstant.USER_ID)) ? "" : URLDecoder.decode(request.getHeader(CommonConstant.USER_ID), StandardCharsets.UTF_8);
    }


    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    public static String getUserName() {
        HttpServletRequest request = getRequest();
        return ObjUtil.isEmpty(request.getHeader(CommonConstant.USER_NAME)) ? "" : URLDecoder.decode(request.getHeader(CommonConstant.USER_NAME), StandardCharsets.UTF_8);
    }


    /**
     * 获取客户端标识
     *
     * @return {@link String}
     */
    public static String getIdentity() {
        HttpServletRequest request = getRequest();
        return request.getHeader(CommonConstant.USER_AGENT);
    }


    /**
     * 获取用户ip
     *
     * @return {@link String}
     */
    public static String getIp() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
