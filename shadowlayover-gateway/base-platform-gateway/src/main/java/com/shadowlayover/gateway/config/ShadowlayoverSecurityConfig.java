package com.shadowlayover.gateway.config;

import com.shadowlayover.gateway.props.ShadowlayoverGatewayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/2-16:41
 * @desc: web security
 * </pre>
 */
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class ShadowlayoverSecurityConfig {

    private final ShadowlayoverGatewayProperties shadowlayoverGatewayProperties;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange()
//                .pathMatchers(ArrayUtil.toArray(shadowlayoverGatewayProperties.getIgnoreUrls(),String.class)).permitAll()//白名单配置
//                .anyExchange()
//                .access(new ReactiveAuthorizationManager<AuthorizationContext>() {
//                    @Override
//                    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext context) {
//                        //todo 校验token准确性, 不校验功能权限
//                        return Mono.just(new AuthorizationDecision(true));
//                    }
//                })
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(new ShadowlayoverAccessDeniedHandler())//处理未授权
//                .authenticationEntryPoint(new ShadowlayoverAuthenticationEntryPoint())//处理未认证
//                .and()
//                .csrf().disable();
        //网关不做拦截
        http.authorizeExchange()
                .anyExchange()
                .permitAll()
                .and().csrf()
                .disable();
        return http.build();
    }
}
