package com.shadowlayover.oauth;

import com.shadowlayover.common.core.listener.LoggingListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/12-14:27
 * @desc: oauth认证服务启动类
 * </pre>
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalAuthentication
public class BaseOauthProviderApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BaseOauthProviderApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
