package com.shadowlayover.gateway.filter;

import com.shadowlayover.gateway.model.constants.FilterOrderedConstants;
import com.shadowlayover.gateway.props.ShadowlayoverGatewayProperties;
import com.shadowlayover.gateway.utils.AccessTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-10:47
 * @desc: token验证过滤器
 * </pre>
 */
@RequiredArgsConstructor
@Configuration
public class PreAuthTokenFilter implements GlobalFilter, Ordered {


    private final ShadowlayoverGatewayProperties securityProperties;

    private final RedisConnectionFactory redisConnectionFactory;

//    @Bean
//    public RedisTokenStore redisTokenStore() {
//        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        redisTokenStore.setPrefix("oauth:");
//        return redisTokenStore;
//    }

    /**
     * 请求地址匹配
     */
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //网关身份
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getURI().getRawPath();

        boolean isIgnorePath = securityProperties.getIgnoreUrls()
                .stream()
                .anyMatch(ignoreUrl -> pathMatcher.match(ignoreUrl, requestPath));
        if (isIgnorePath) {
            return chain.filter(exchange);
        }

        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        AccessTokenUtils.checkTokenFromHead(redisTokenStore(), authorization);
        //校验地址是否需要权限
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return FilterOrderedConstants.PRE_AUTH_TOKE;
    }
}
