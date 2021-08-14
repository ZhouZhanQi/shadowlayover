package com.shadowlayover.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/12-14:27
 * @desc: oauth认证服务启动类
 * </pre>
 */
@EnableGlobalAuthentication
public class BaseOauthServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BaseOauthServerApplication.class, args);
    }
}
