package com.buni.bunigateway.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.buni.buniframework.config.redis.RedisService;
import com.buni.buniframework.constant.CommonConstant;
import com.buni.buniframework.enums.ResultEnum;
import com.buni.buniframework.util.Result;
import com.buni.bunigateway.constant.PublicUrlConstant;
import com.buni.usercommon.entity.Authority;
import com.buni.usercommon.enums.BooleanEnum;
import com.buni.usercommon.vo.login.UserLoginVO;
import com.buni.usercommon.vo.role.AuthorityDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author zp.wei
 * @date 2023/9/18 13:39
 */
@Slf4j
@Order(value = 2)
@Component
@AllArgsConstructor
public class GateWayFilter implements GlobalFilter {

    private PublicUrlConstant publicUrlConstant;
    private RedisService redisService;


    /**
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest(); //请求
        //获取请求路径，判断是否是公共接口
        //针对路径进行处理
        String path = getPath(request);
        if (!publicUrlConstant.getPublicUrl().contains(path)) {
            String token = getToken(request);
            String tokenKey = CommonConstant.TOKEN_REDIS_KEY + token;
            //从redis中获取当前登录用户的信息
            UserLoginVO userLoginVO = (UserLoginVO) redisService.get(tokenKey);
            //校验token，如果token正确则放行
            if (ObjUtil.isEmpty(userLoginVO)) {
                log.error("---------- token为空 ----------");
                return returnMsg(exchange, ResultEnum.UNAUTHORIZED);
            }
            if (userLoginVO.getTokenVO().getExpireTime() < System.currentTimeMillis()) {
                log.error("---------- token已失效 ----------");
                return returnMsg(exchange, ResultEnum.INVALID_TOKEN);
            }
            //校验是否是超级管理员,如果是超级管理员则放行，否则校验是否拥有接口权限
            if (userLoginVO.getIsAdmin().equals(BooleanEnum.NO)) {
                //校验是否有对应的接口权限
                List<AuthorityDTO> authorityList = (List<AuthorityDTO>) redisService.get(Authority.REDIS_KEY + userLoginVO.getId());
                if (CollUtil.isEmpty(authorityList) || authorityList.stream().map(AuthorityDTO::getUrl).noneMatch(path::equals)) {
                    return returnMsg(exchange, ResultEnum.ACCESS_DENIED);
                }
            }
            //给token重新生成过期时间，进行有效期延长
            userLoginVO.getTokenVO().setExpireTime(System.currentTimeMillis() + CommonConstant.EXPIRE_TIME_MS);
            //将用户信息存入请求头中
            request.mutate().header(CommonConstant.USER_ID, String.valueOf(userLoginVO.getId())).build();
            request.mutate().header(CommonConstant.USER_NAME, userLoginVO.getUsername()).build();
        }
        //给请求头中加相应的设置，避免绕过网关直接请求对应的服务
        request.mutate().header(CommonConstant.GATEWAY_KEY, RandomUtil.randomString(32)).build();
        //结束处理
        return chain.filter(exchange.mutate().request(request).build());
    }


    /**
     * 获取请求token
     *
     * @param request
     * @return
     */
    private String getToken(ServerHttpRequest request) {
        String token = CommonConstant.EMPTY_STR;
        if (ObjUtil.isNotEmpty(request.getHeaders().get(CommonConstant.AUTHORIZATION))) {
            token = Objects.requireNonNull(request.getHeaders().get(CommonConstant.AUTHORIZATION)).get(0);
        }
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (ObjUtil.isNotEmpty(token) && token.startsWith(CommonConstant.PREFIX)) {
            token = StrUtil.subAfter(token, CommonConstant.PREFIX, true);
        }
        return token;
    }

    /**
     * 获取请求的path
     *
     * @param request
     * @return
     */
    private String getPath(ServerHttpRequest request) {
        String path;
        String requestPath = request.getURI().getPath();
        String method = String.valueOf(request.getMethod());
        String pathSuffix = ReUtil.get("/(\\d+)$", requestPath, 1);
        if (ObjUtil.isNotEmpty(pathSuffix)) {
            path = ReUtil.replaceFirst(Pattern.compile("/(\\d+)$"), requestPath, "/{id}/" + method);
        } else {
            path = requestPath + "/" + method;
        }
        return path;
    }


    /**
     * 异常返回
     *
     * @param exchange
     * @param resultEnum
     * @return
     */
    public Mono<Void> returnMsg(ServerWebExchange exchange, ResultEnum resultEnum) {
        ServerHttpResponse response = exchange.getResponse();
        Result<Object> result = Result.error(resultEnum);
        byte[] data = new byte[0];
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.writeValueAsBytes(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(HttpStatusCode.valueOf(resultEnum.getCode()));
        return response.writeWith(Mono.just(buffer));
    }


}
