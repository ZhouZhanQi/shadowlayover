package com.shadowlayover.gateway.filter;

import com.shadowlayover.gateway.model.constants.FilterOrderedConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-10:51
 * @desc: 规则过滤器
 * </pre>
 */
public class PreRuleFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }
    
    @Override
    public int getOrder() {
        return FilterOrderedConstants.PRE_RULE;
    }
}
