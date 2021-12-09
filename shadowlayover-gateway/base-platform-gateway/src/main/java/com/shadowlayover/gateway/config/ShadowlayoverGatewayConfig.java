package com.shadowlayover.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/30-14:38
 * @desc: 网关配置类
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
public class ShadowlayoverGatewayConfig {
//
//
//    @Bean
//    @Primary
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}
