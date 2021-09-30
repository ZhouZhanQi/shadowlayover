package com.shadowlayover.common.feign.config;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.ShadowlayoverFeignClientRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-10:36
 * @desc: feign自动配置
 * </pre>
 */
@Slf4j
@Configuration
@ConditionalOnClass(Feign.class)
@Import(ShadowlayoverFeignClientRegister.class)
@AutoConfigureAfter(EnableFeignClients.class)
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
