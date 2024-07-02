package com.buni.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 根据ip地址限流
 *
 * @author zp.wei
 * @date 2024/5/21 13:14
 */
@Component
public class IpAddressKeyResolver implements KeyResolver {


    /**
     * 根据ip地址限流
     *
     * @param exchange
     * @return
     */
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostString());
    }


}
