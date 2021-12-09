//package com.shadowlayover.gateway.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
///**
// * <pre>
// * @author: zhouzhanqi
// * @datetime: 2021/11/2-16:41
// * @desc: web security
// * </pre>
// */
//@RequiredArgsConstructor
//@EnableWebFluxSecurity
//public class ShadowlayoverSecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        //网关不做拦截
//        http.authorizeExchange()
//                .anyExchange()
//                .permitAll()
//                .and()
//                .csrf()
//                .disable()
//                .httpBasic()
//                .disable();
//        return http.build();
//    }
//}
