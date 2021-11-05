package com.shadowlayover.common.cache.config;

import com.shadowlayover.common.cache.layer.ShadowlayoverCacheManager;
import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/31-17:18
 * @desc: redis配置
 * </pre>
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CacheRedisCaffeineProperties.class)
@Import(RedisConfiguration.class)
public class RedisCaffeineAutoConfig {

    @Autowired
    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Bean
    @Primary
    public ShadowlayoverCacheManager cacheManager(CaffeineCacheManager caffeineCacheManager, RedisCacheManager redisCacheManager) {
        ShadowlayoverCacheManager cacheManager = new ShadowlayoverCacheManager();
        cacheManager.setCaffeineCacheManager(caffeineCacheManager);
        cacheManager.setRedisCacheManager(redisCacheManager);
        cacheManager.setCacheRedisCaffeineProperties(cacheRedisCaffeineProperties);
        return cacheManager;
    }
}
