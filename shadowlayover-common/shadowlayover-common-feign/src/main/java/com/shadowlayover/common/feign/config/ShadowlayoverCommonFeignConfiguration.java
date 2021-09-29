package com.shadowlayover.common.feign.config;

import com.shadowlayover.common.feign.fallback.ShadowlayoverFallbackFactory;
import feign.FeignException;
import feign.Logger;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-11:28
 * @desc:
 * </pre>
 */
public class ShadowlayoverCommonFeignConfiguration {
    
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }
    
    @Bean
    public ErrorDecoder errorDecoder() {
        //分离异常类型
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                //区分业务异常已服务异常
                return FeignException.errorStatus(methodKey, response);
            }
        };
    }
}
