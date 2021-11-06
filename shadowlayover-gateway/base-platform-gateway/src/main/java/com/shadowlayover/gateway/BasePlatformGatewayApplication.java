package com.shadowlayover.gateway;

import com.shadowlayover.common.cache.annotation.EnableShadowlayoverCache;
import com.shadowlayover.common.core.listener.LoggingListener;
import com.shadowlayover.common.core.model.constants.CoreConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/20-13:58
 * @desc: 平台网关启动类
 * </pre>
 */
@EnableShadowlayoverCache
@SpringBootApplication
@EnableDiscoveryClient
public class BasePlatformGatewayApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BasePlatformGatewayApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
