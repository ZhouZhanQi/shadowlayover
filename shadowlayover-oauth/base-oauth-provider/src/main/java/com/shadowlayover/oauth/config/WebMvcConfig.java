package com.shadowlayover.oauth.config;

import com.shadowlayover.common.web.interceptor.LoginUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/30-20:10
 * @desc:
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginUserInterceptor())
                .excludePathPatterns("/base-oauth/oauth/**");
    }
}
