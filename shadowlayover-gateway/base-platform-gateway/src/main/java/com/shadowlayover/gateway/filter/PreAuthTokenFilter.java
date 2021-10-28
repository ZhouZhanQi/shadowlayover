package com.shadowlayover.gateway.filter;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.common.security.model.code.SecurityExceptionCode;
import com.shadowlayover.common.security.utils.AccessTokenUtils;
import com.shadowlayover.gateway.config.ShadowlayoverSecurityProperties;
import com.shadowlayover.gateway.model.constants.FilterOrderedConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-10:47
 * @desc: token验证过滤器
 * </pre>
 */
@RequiredArgsConstructor
@Component
public class PreAuthTokenFilter implements GlobalFilter, Ordered {


    private final ShadowlayoverSecurityProperties securityProperties;

    private final TokenStore tokenStore;

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
        AccessTokenUtils.checkTokenFromHead(tokenStore, authorization);
        //校验地址是否需要权限
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return FilterOrderedConstants.PRE_AUTH_TOKE;
    }
}
