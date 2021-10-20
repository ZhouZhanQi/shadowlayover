package com.shadowlayover.gateway.filter;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.generator.UUIDGenerator;
import com.shadowlayover.common.core.model.constants.CoreConstants;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/20-14:38
 * @desc: 请求前过滤器
 * </pre>
 */
@Component
public class PreRequestFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //检查是否开启
        String traceId = new UUIDGenerator().next();
        MDC.put(CoreConstants.LOG_TRACE_ID, traceId);
        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .headers(header -> header.add(CoreConstants.SHADOWLOYOVER_TRACE_ID, traceId))
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }
    
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
