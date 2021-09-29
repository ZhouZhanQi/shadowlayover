package com.shadowlayover.common.web.config;

import com.shadowlayover.common.web.handler.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-14:14
 * @desc:
 * </pre>
 */
@Slf4j
@Configuration
public class ShadowlayoverWebAutoConfiguration {
    
    @Bean
    public GlobalExceptionHandler exceptionHandler() {
        log.info(">>> shadowlayover global exception handler init ...");
        return new GlobalExceptionHandler();
    }
}
