package com.shadowlayover.oauth;

import com.shadowlayover.common.cache.annotation.EnableShadowlayoverCache;
import com.shadowlayover.common.core.listener.LoggingListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/12-14:27
 * @desc: oauth认证服务启动类
 * </pre>
 */
@EnableShadowlayoverCache
@SpringBootApplication
@EnableDiscoveryClient
public class BaseOauthApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BaseOauthApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
