package com.shadowlayover.common.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-10:36
 * @desc: feign自动配置
 * </pre>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class ShadowlayoverFeignAutoConfiguration {

    @Bean("shadowlayoverFeignRequestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                //传递上下文信息
            }
        };
    }
}
