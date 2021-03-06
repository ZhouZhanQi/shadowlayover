package com.shadowlayover.common.cache.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.shadowlayover.common.cache.layer.ShadowlayoverCaffeineCacheManager;
import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/5-9:46
 * @desc: caffeine缓存配置
 * </pre>
 */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@EnableConfigurationProperties(CacheRedisCaffeineProperties.class)
public class CaffeineConfiguration {

    private final CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Bean
    @ConditionalOnMissingBean(CaffeineCacheManager.class)
    public CaffeineCacheManager caffeineCacheManager() {
        ShadowlayoverCaffeineCacheManager caffeineCacheManager = new ShadowlayoverCaffeineCacheManager(cacheRedisCaffeineProperties);
//        caffeineCacheManager.setCaffeine(caffeine());
        return caffeineCacheManager;
    }
}
