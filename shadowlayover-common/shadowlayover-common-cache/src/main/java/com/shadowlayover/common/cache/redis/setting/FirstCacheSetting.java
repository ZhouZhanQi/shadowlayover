package com.shadowlayover.common.cache.redis.setting;

import lombok.Builder;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/31-17:05
 * @desc: 一级缓存配置
 * </pre>
 */
@Getter
@Builder
public class FirstCacheSetting {
    
    
    /**
     * 一级缓存配置
     */
    private String cacheSpecification;
}
