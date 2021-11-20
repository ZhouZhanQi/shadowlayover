package com.shadowlayover.example;

import com.shadowlayover.common.core.listener.LoggingListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/17-11:38
 * @desc: 启动类
 * </pre>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ExampleOrderApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ExampleOrderApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
