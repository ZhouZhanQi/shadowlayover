package com.shadowlayover.message.config;

import com.shadowlayover.message.client.fallback.SysNoticeRemoteFallback;
import org.springframework.context.annotation.Bean;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-11:28
 * @desc:
 * </pre>
 */
public class MessageFeignConfiguration {
    @Bean
    public SysNoticeRemoteFallback echoServiceFallback() {
        return new SysNoticeRemoteFallback();
    }
}
