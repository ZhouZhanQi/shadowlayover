package com.shadowlayover.common.cache.config;

import com.shadowlayover.common.cache.layer.ShadowlayoverCacheManager;
import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
 * @datetime: 2021/11/5-17:00
 * @desc: 缓存自动配置
 * </pre>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CacheRedisCaffeineProperties.class)
@Import({RedisConfiguration.class, CaffeineConfiguration.class})
public class ShadowlayoverAutoConfiguration {

    @Autowired
    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Bean
    @Primary
    public ShadowlayoverCacheManager cacheManager(CaffeineCacheManager caffeineCacheManager, RedisCacheManager redisCacheManager) {
        log.info(">>> shadowlayover enable layer cache");
        ShadowlayoverCacheManager cacheManager = new ShadowlayoverCacheManager();
        cacheManager.setCaffeineCacheManager(caffeineCacheManager);
        cacheManager.setRedisCacheManager(redisCacheManager);
        cacheManager.setCacheRedisCaffeineProperties(cacheRedisCaffeineProperties);
        return cacheManager;
    }
}
