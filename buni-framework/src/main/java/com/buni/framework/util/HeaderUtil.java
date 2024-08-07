package com.buni.framework.util;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
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
        return ObjUtil.isNotEmpty(servletRequestAttributes) ? servletRequestAttributes.getRequest() : null;
    }


    /**
     * 获取用户token
     *
     * @return {@link String}
     */
    public static String getToken() {
        try {
            HttpServletRequest request = getRequest();
            String token = request.getHeader(CommonConstant.AUTHORIZATION);
            return ObjUtil.isEmpty(token) ? CommonConstant.EMPTY_STR : StrUtil.subAfter(token, CommonConstant.PREFIX, true);
        } catch (Exception e) {
            return CommonConstant.EMPTY_STR;
        }
    }


    /**
     * 获取用户id
     *
     * @return {@link String}
     */
    public static Long getUserId() {
        try {
            HttpServletRequest request = getRequest();
            Long userId = Long.valueOf(request.getHeader(CommonConstant.USER_ID));
            return ObjUtil.isEmpty(userId) ? CommonConstant.ZERO : userId;
        } catch (Exception e) {
            return Long.valueOf(CommonConstant.ZERO);
        }
    }


    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    public static String getUserName() {
        HttpServletRequest request = getRequest();
        try {
            String username = URLDecoder.decode(request.getHeader(CommonConstant.USER_NAME), StandardCharsets.UTF_8);
            return ObjUtil.isEmpty(username) ? CommonConstant.EMPTY_STR : username;
        } catch (Exception e) {
            return CommonConstant.EMPTY_STR;
        }
    }


    /**
     * 获取客户端标识
     *
     * @return {@link String}
     */
    public static String getIdentity() {
        try {
            HttpServletRequest request = getRequest();
            String identity = request.getHeader(CommonConstant.USER_AGENT);
            return ObjUtil.isEmpty(identity) ? CommonConstant.EMPTY_STR : identity;
        } catch (Exception e) {
            return CommonConstant.EMPTY_STR;
        }
    }


    /**
     * 获取用户ip
     *
     * @return {@link String}
     */
    public static String getIp() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader(CommonConstant.FORWARDED);
        if (StrUtil.isNotEmpty(ip) && !CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(CommonConstant.COMMA)) {
                ip = ip.split(CommonConstant.COMMA)[CommonConstant.ZERO];
            }
        }
        if (StrUtil.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(CommonConstant.PROXY_CLIENT_IP);
        }
        if (StrUtil.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(CommonConstant.WL_PROXY_CLIENT_IP);
        }
        if (StrUtil.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(CommonConstant.HTTP_CLIENT_IP);
        }
        if (StrUtil.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(CommonConstant.HTTP_X_FORWARDED_FOR);
        }
        if (StrUtil.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(CommonConstant.X_REAL_IP);
        }
        if (StrUtil.isEmpty(ip) || CommonConstant.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
