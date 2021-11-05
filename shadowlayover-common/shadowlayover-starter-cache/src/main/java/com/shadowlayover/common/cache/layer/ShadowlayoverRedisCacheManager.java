package com.shadowlayover.common.cache.layer;

import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import org.apache.commons.collections4.MapUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;
import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/4-17:59
 * @desc: redis缓存管理
 * </pre>
 */
public class ShadowlayoverRedisCacheManager extends RedisCacheManager {

    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    public ShadowlayoverRedisCacheManager(CacheRedisCaffeineProperties cacheRedisCaffeineProperties, RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
        this.cacheRedisCaffeineProperties = cacheRedisCaffeineProperties;
    }

    public ShadowlayoverRedisCacheManager(CacheRedisCaffeineProperties cacheRedisCaffeineProperties, RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, CacheRedisCaffeineProperties cacheRedisCaffeineProperties1, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
        this.cacheRedisCaffeineProperties = cacheRedisCaffeineProperties1;
    }

    public ShadowlayoverRedisCacheManager(CacheRedisCaffeineProperties cacheRedisCaffeineProperties, RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, CacheRedisCaffeineProperties cacheRedisCaffeineProperties1, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
        this.cacheRedisCaffeineProperties = cacheRedisCaffeineProperties1;
    }

    public ShadowlayoverRedisCacheManager(CacheRedisCaffeineProperties cacheRedisCaffeineProperties, RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, CacheRedisCaffeineProperties cacheRedisCaffeineProperties1) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
        this.cacheRedisCaffeineProperties = cacheRedisCaffeineProperties1;
    }

    public ShadowlayoverRedisCacheManager(CacheRedisCaffeineProperties cacheRedisCaffeineProperties, RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation, CacheRedisCaffeineProperties cacheRedisCaffeineProperties1) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
        this.cacheRedisCaffeineProperties = cacheRedisCaffeineProperties1;
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        cacheConfig.prefixCacheNameWith(cacheRedisCaffeineProperties.getCachePrefix());
        Map<String, Long> expires = cacheRedisCaffeineProperties.getRedis().getExpires();
        if (MapUtils.isNotEmpty(expires)) {
            if (expires.containsKey(name)) {
                Long timeExpire = expires.get(name);
                cacheConfig = cacheConfig.entryTtl(Duration.ofMillis(timeExpire));
            }
        }
        return super.createRedisCache(name, cacheConfig);
    }
}
