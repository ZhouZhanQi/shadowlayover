package com.shandowlayover.webflux;

import com.shadowlayover.common.core.listener.LoggingListener;
import com.shadowlayover.common.web.annotation.EnableShadowlayoverAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author zhouzhanqi
 */
@EnableCaching
@SpringBootApplication
public class ShadowlayoverWebfluxApplication {
    public static void main(String[] args) {
        // SpringApplication.run(ShadowlayoverWebfluxApplication.class, args);
        SpringApplication application = new SpringApplication(ShadowlayoverWebfluxApplication.class);
        application.addListeners(new LoggingListener());
        application.run(args);
    }
}
