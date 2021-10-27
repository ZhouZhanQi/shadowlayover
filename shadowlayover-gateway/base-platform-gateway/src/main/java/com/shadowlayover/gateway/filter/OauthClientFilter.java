package com.shadowlayover.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/27-14:04
 * @desc: 网关授权client过滤器
 * </pre>
 */
public class OauthClientFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //网关身份
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        return chain.filter(exchange.mutate().request(builder.build()).build());
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
