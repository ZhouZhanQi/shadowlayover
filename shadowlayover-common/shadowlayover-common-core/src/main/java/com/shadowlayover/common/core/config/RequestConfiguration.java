package com.shadowlayover.common.core.config;

import com.shadowlayover.common.core.filter.ShadowlayoverTraceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-9:50
 * @desc: 请求配置
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
public class RequestConfiguration {
    
    @Bean
    public ShadowlayoverTraceFilter traceFilter() {
        return new ShadowlayoverTraceFilter();
    }
}
