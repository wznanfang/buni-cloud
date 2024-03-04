package com.buni.buniframework.config.filter;

import cn.hutool.core.util.ObjUtil;
import com.buni.buniframework.constant.CommonConstant;
import com.buni.buniframework.enums.ResultEnum;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 全局过滤器
 *
 * @author zp.wei
 * @date 2023/9/21 10:56
 */
@Slf4j
@Component
public class AccessControlFilter implements Filter {


    /**
     * 网关在请求头添加的信息，如果请求头有这个值，表示已经经过网关认证，可以访问微服务
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader(CommonConstant.GATEWAY_KEY);
        log.info("----------token: " + token);
        if (ObjUtil.isEmpty(token)) {
            response.sendError(ResultEnum.UNAUTHORIZED.getCode());
            return;
        }
        filterChain.doFilter(request, response);
    }


}
