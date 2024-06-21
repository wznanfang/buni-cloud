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
import java.util.Optional;

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
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }


    /**
     * 获取用户token
     *
     * @return {@link String}
     */
    public static String getToken() {
        HttpServletRequest request = getRequest();
        if (ObjUtil.isNotEmpty(request)) {
            return ObjUtil.isEmpty(request.getHeader(CommonConstant.AUTHORIZATION)) ? CommonConstant.EMPTY_STR :
                    StrUtil.subAfter(request.getHeader(CommonConstant.AUTHORIZATION), CommonConstant.PREFIX, true);
        }
        return CommonConstant.EMPTY_STR;
    }


    /**
     * 获取用户id
     *
     * @return {@link String}
     */
    public static Long getUserId() {
        HttpServletRequest request = getRequest();
        if (ObjUtil.isNotEmpty(request)) {
            return ObjUtil.isEmpty(request.getHeader(CommonConstant.USER_ID)) ? CommonConstant.ZERO : Long.parseLong(request.getHeader(CommonConstant.USER_ID));
        }
        return Long.valueOf(CommonConstant.ZERO);
    }


    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    public static String getUserName() {
        HttpServletRequest request = getRequest();
        if (ObjUtil.isNotEmpty(request)) {
            return ObjUtil.isEmpty(URLDecoder.decode(request.getHeader(CommonConstant.USER_NAME), StandardCharsets.UTF_8))
                    ? CommonConstant.EMPTY_STR : URLDecoder.decode(request.getHeader(CommonConstant.USER_NAME), StandardCharsets.UTF_8);
        }
        return CommonConstant.EMPTY_STR;
    }


    /**
     * 获取客户端标识
     *
     * @return {@link String}
     */
    public static String getIdentity() {
        HttpServletRequest request = getRequest();
        if (ObjUtil.isNotEmpty(request)) {
            return ObjUtil.isEmpty(request.getHeader(CommonConstant.USER_AGENT)) ? CommonConstant.EMPTY_STR : request.getHeader(CommonConstant.USER_AGENT);
        }
        return CommonConstant.EMPTY_STR;
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
