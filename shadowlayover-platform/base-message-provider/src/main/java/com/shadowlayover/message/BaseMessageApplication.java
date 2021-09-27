package com.shadowlayover.message;

import com.shadowlayover.common.core.listener.LoggingListener;
import com.shadowlayover.common.web.annotation.EnableShadowlayoverAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CarlosChou
 * @date 2021/7/27 1:00
 * @desc 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableShadowlayoverAsync
public class BaseMessageApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BaseMessageApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
