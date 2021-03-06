package com.shadowlayover.common.cache.support;

import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.cache.RedisCache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/3-9:54
 * @desc:
 * </pre>
 */
@Slf4j
public class RedisCaffeineCache extends AbstractValueAdaptingCache {

    @Getter
    private String name;

    private RedisCache redisCache;

    private CaffeineCache caffeineCache;

    private boolean clearRemoteOnExit;

    public RedisCaffeineCache(String name, RedisCache redisCache, CaffeineCache caffeineCache, CacheRedisCaffeineProperties cacheRedisCaffeineProperties) {
        super(cacheRedisCaffeineProperties.isCacheNullValues());
        this.name = name;
        this.redisCache = redisCache;
        this.caffeineCache = caffeineCache;
        this.clearRemoteOnExit = cacheRedisCaffeineProperties.isClearRemoteOnExit();
    }

    @Override
    protected Object lookup(Object key) {
        Object value = caffeineCache.get(key);
        if(Objects.nonNull(value)) {
            log.debug(">>> get cache value from caffeine, key: {}", key);
            return value;
        }

        value = redisCache.get(key);
        if(Objects.nonNull(value)) {
            log.debug(">>> get cache value from caffeine and put value to redis, key : {}", key);
            caffeineCache.put(key, value);
        }
        return value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public <T> T get(Object key, Callable<T> callable) {
        return (T) caffeineCache.getNativeCache().get(key, k -> getRedisStoreValue(k, callable));
    }

    private <T> Object getRedisStoreValue(Object key, Callable<T> callable) {
        T value = redisCache.get(key, callable);
        return toStoreValue(value);
    }

    @Override
    public void put(Object key, Object value) {
        if (!super.isAllowNullValues() && value == null) {
            this.evict(key);
            return;
        }

        redisCache.put(key, toStoreValue(value));
//        push(new CacheMessage(this.name, key));
        caffeineCache.put(key, value);
    }

    @Override
    public void evict(Object key) {
        // ?????????redis??????????????????????????????caffeine????????????????????????????????????????????????caffeine??????????????????????????????redis????????????caffeine???
       redisCache.evict(key);
       caffeineCache.evict(key);
    }

    @Override
    public void clear() {
        // ?????????redis??????????????????????????????caffeine????????????????????????????????????????????????caffeine??????????????????????????????redis????????????caffeine???
//        redisCache.clear();
        if (clearRemoteOnExit) {
            redisCache.clear();
        }
        caffeineCache.clear();
    }

}
