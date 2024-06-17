package com.buni.gateway.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.buni.framework.config.redis.RedisService;
import com.buni.framework.constant.CommonConstant;
import com.buni.framework.enums.ResultEnum;
import com.buni.framework.util.Result;
import com.buni.gateway.properties.PublicUrlProperties;
import com.buni.user.entity.Authority;
import com.buni.user.enums.BooleanEnum;
import com.buni.user.vo.login.UserLoginVO;
import com.buni.user.vo.role.AuthorityDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

/**
 * @author zp.wei
 * @date 2023/9/18 13:39
 */
@Slf4j
@Order(value = 2)
@Component
@AllArgsConstructor
public class GateWayFilter implements GlobalFilter {

    private PublicUrlProperties publicUrlProperties;
    private RedisService redisService;


    /**
     * @param exchange 请求上下文
     * @param chain    过滤器链
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求路径，判断是否是公共接口
        // 针对路径进行处理
        String path = getPath(request);
        if (!publicUrlProperties.getPublicUrl().contains(path)) {
            String token = getToken(request);
            String tokenKey = CommonConstant.TOKEN_REDIS_KEY + token;
            // 从redis中获取当前登录用户的信息
            UserLoginVO userLoginVO = (UserLoginVO) redisService.get(tokenKey);
            // 校验token，如果token正确则放行
            if (ObjUtil.isEmpty(userLoginVO) || userLoginVO.getTokenVO().getExpireTime() < System.currentTimeMillis()) {
                return returnMsg(exchange, ResultEnum.UNAUTHORIZED);
            }
            // 校验是否是超级管理员,如果是超级管理员则放行，否则校验是否拥有接口权限
            if (userLoginVO.getAdmin().equals(BooleanEnum.NO)) {
                // 校验是否有对应的接口权限
                List<String> urls = getUrls(userLoginVO.getId());
                if (CollUtil.isEmpty(urls) || !urls.contains(path)) {
                    return returnMsg(exchange, ResultEnum.ACCESS_DENIED);
                }
            }
            // 给token重新生成过期时间，进行有效期延长
            userLoginVO.getTokenVO().setExpireTime(System.currentTimeMillis() + CommonConstant.EXPIRE_TIME_MS);
            redisService.setOneHour(tokenKey, userLoginVO);
            // 将用户信息存入请求头中
            request.mutate().header(CommonConstant.USER_ID, URLEncoder.encode(String.valueOf(userLoginVO.getId()), StandardCharsets.UTF_8)).build();
            request.mutate().header(CommonConstant.USER_NAME, URLEncoder.encode(userLoginVO.getUsername(), StandardCharsets.UTF_8)).build();
        }
        // 给请求头中加相应的设置，避免绕过网关直接请求对应的服务
        request.mutate().header(CommonConstant.GATEWAY_KEY, RandomUtil.randomString(32)).build();
        // 结束处理
        return chain.filter(exchange.mutate().request(request).build());
    }


    /**
     * 获取请求token
     *
     * @param request 请求
     * @return token
     */
    private String getToken(ServerHttpRequest request) {
        String token = CommonConstant.EMPTY_STR;
        if (ObjUtil.isNotEmpty(request.getHeaders().get(CommonConstant.AUTHORIZATION))) {
            token = Objects.requireNonNull(request.getHeaders().getFirst(CommonConstant.AUTHORIZATION));
        }
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (ObjUtil.isNotEmpty(token) && token.startsWith(CommonConstant.PREFIX)) {
            token = StrUtil.subAfter(token, CommonConstant.PREFIX, true);
        }
        return token;
    }

    /**
     * 获取当前用户的接口权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    private List<String> getUrls(Long userId) {
        List<String> urlList = new ArrayList<>();
        List<AuthorityDTO> authorityList = (List<AuthorityDTO>) redisService.get(Authority.REDIS_KEY + userId);
        if (CollUtil.isNotEmpty(authorityList)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode jsonNode = mapper.readTree(JSONUtil.toJsonStr(authorityList));
                // 提取每个对象的 url 值并分割成数组
                urlList = List.of(StreamSupport.stream(jsonNode.spliterator(), false).map(node -> node.get(CommonConstant.URL).asText())
                        .flatMap(url -> Arrays.stream(url.split(CommonConstant.COMMA))).map(String::trim).toArray(String[]::new));
            } catch (Exception e) {
                log.error("接口权限获取失败{}", e.getMessage());
            }
        }
        return urlList;
    }

    /**
     * 获取请求的path
     *
     * @param request 请求
     * @return path
     */
    private String getPath(ServerHttpRequest request) {
        String requestPath = request.getURI().getPath();
        String method = String.valueOf(request.getMethod());
        String pathSuffix = ReUtil.get("/(\\d+)$", requestPath, CommonConstant.ONE);
        String path = requestPath + CommonConstant.FORWARD_SLASH + method;
        if (ObjUtil.isNotEmpty(pathSuffix)) {
            path = ReUtil.replaceFirst(Pattern.compile("/(\\d+)$"), requestPath, "/{}/" + method);
        }
        return path;
    }


    /**
     * 异常返回
     *
     * @param exchange   请求上下文
     * @param resultEnum 异常枚举
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    public Mono<Void> returnMsg(ServerWebExchange exchange, ResultEnum resultEnum) {
        ServerHttpResponse response = exchange.getResponse();
        Result<Object> result = Result.error(resultEnum);
        byte[] data = new byte[CommonConstant.ZERO];
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.writeValueAsBytes(result);
        } catch (JsonProcessingException e) {
            log.error("json转换失败{}", e.getMessage());
        }
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(HttpStatusCode.valueOf(resultEnum.getCode()));
        return response.writeWith(Mono.just(buffer));
    }


}
