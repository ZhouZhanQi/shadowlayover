package com.shadowlayover.component;

import com.shadowlayover.common.core.listener.LoggingListener;
import com.shadowlayover.common.feign.config.ShadowlayoverCommonFeignConfiguration;
import com.shadowlayover.common.web.annotation.EnableShadowlayoverAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CarlosChou
 * @date 2021/7/27 1:00
 * @desc 启动类
 */
@EnableFeignClients(basePackages = {"com.shadowlayover.*.client", "com.shadowlayover.*.fallback"}, defaultConfiguration = ShadowlayoverCommonFeignConfiguration.class)
@SpringBootApplication
@EnableDiscoveryClient
@EnableShadowlayoverAsync
public class BaseComponentApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BaseComponentApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
