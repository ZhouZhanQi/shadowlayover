package com.shadowlayover.message;

import com.shadowlayover.common.core.listener.LoggingListener;
import com.shadowlayover.common.core.model.constants.CoreConstants;
import com.shadowlayover.common.feign.config.ShadowlayoverCommonFeignConfiguration;
import com.shadowlayover.common.web.annotation.EnableShadowlayoverAsync;
import com.shadowlayover.message.model.constants.MessageConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CarlosChou
 * @date 2021/7/27 1:00
 * @desc 启动类
 */
@EnableFeignClients(basePackages = {CoreConstants.BASE_CLIENT_PACKAGE}, defaultConfiguration = ShadowlayoverCommonFeignConfiguration.class)
@SpringBootApplication
@ComponentScan(basePackages = {MessageConstants.SERVICE_BASE_PACKAGE, CoreConstants.BASE_CLIENT_PACKAGE})
@EnableDiscoveryClient
@EnableShadowlayoverAsync
public class BaseMessageApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BaseMessageApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
