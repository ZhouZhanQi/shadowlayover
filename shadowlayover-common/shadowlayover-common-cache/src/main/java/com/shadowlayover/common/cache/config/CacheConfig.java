package com.shadowlayover.common.cache.config;

import com.shadowlayover.common.cache.layering.LayeringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/31-17:22
 * @desc: 缓存配置
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
public class CacheConfig {
    
    @Bean
    @Primary
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        LayeringCacheManager layeringCacheManager = new LayeringCacheManager(redisTemplate);
        // Caffeine缓存设置
        setFirstCacheConfig(layeringCacheManager);
        
        // redis缓存设置
        setSecondaryCacheConfig(layeringCacheManager);
        
        // 允许存null，防止缓存击穿
        layeringCacheManager.setAllowNullValues(true);
        return layeringCacheManager;
    }
    
}
