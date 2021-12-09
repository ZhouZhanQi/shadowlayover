package com.shadowlayover.gateway.filter;

import com.shadowlayover.common.cache.RedisCacheHelper;
import com.shadowlayover.common.cache.model.code.RedisKeyPrefix;
import com.shadowlayover.common.core.exceptions.FrameworkException;
import com.shadowlayover.common.core.model.LoginUser;
import com.shadowlayover.common.core.model.constants.CoreConstants;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.common.core.utils.JacksonUtils;
import com.shadowlayover.gateway.model.code.BasePlatformGatewayExceptionCode;
import com.shadowlayover.gateway.model.constants.FilterOrderedConstants;
import com.shadowlayover.gateway.utils.AccessTokenUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-10:51
 * @desc: 权限认证后
 * </pre>
 */
@RequiredArgsConstructor
@Component
public class AfterAuthFilter implements GlobalFilter, Ordered {

    private final RedisCacheHelper redisCacheHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //网关身份
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {
            return chain.filter(exchange);
        }

        String token = AccessTokenUtils.getTokenFromHead(authorization);
        LoginUser loginUser = (LoginUser) redisCacheHelper.get(RedisKeyPrefix.OAUTH_TOKEN_USER, token);
        AssertUtils.checkNotNull(loginUser, new FrameworkException(BasePlatformGatewayExceptionCode.TOKEN_USER_INFO_EXPIRED));
        //设置用户信息
        request.mutate()
                .headers(header -> header.add(CoreConstants.SHADOWLOYOVER_USER_INFO, Base64.getEncoder().encodeToString(JacksonUtils.pojo2Json(loginUser).getBytes(StandardCharsets.UTF_8))))
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return FilterOrderedConstants.AFTER_AUTH_TOKEN;
    }
}
