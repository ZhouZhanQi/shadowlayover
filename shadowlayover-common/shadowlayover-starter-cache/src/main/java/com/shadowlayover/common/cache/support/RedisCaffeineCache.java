package com.shadowlayover.common.cache.support;

import com.github.benmanes.caffeine.cache.Cache;
import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import com.shadowlayover.common.cache.lock.RedisLock;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
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

    private String cachePrefix;

    private long defaultExpiration = 0;

    private Map<String, Long> expires;

    private String topic = "cache:redis:caffeine:topic";

    private Map<String, ReentrantLock> keyLockMap = new ConcurrentHashMap<>();

    protected RedisCaffeineCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    public RedisCaffeineCache(String name, RedisCache redisCache, CaffeineCache caffeineCache, CacheRedisCaffeineProperties cacheRedisCaffeineProperties) {
        super(cacheRedisCaffeineProperties.isCacheNullValues());
        this.name = name;
        this.redisCache = redisCache;
        this.caffeineCache = caffeineCache;
        this.cachePrefix = cacheRedisCaffeineProperties.getCachePrefix();
        this.defaultExpiration = cacheRedisCaffeineProperties.getRedis().getDefaultExpiration();
        this.expires = cacheRedisCaffeineProperties.getRedis().getExpires();
        this.topic = cacheRedisCaffeineProperties.getRedis().getTopic();
    }

    @Override
    protected Object lookup(Object key) {
        Object value = caffeineCache.get(getKey(key));
        if(Objects.nonNull(value)) {
            log.debug(">>> get cache value from caffeine, key: {}", key);
            return value;
        }

        value = redisCache.get(getKey(key));
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
        return (T) caffeineCache.getNativeCache().get(getKey(key), k -> getRedisStoreValue(k, callable));
    }

    private <T> Object getRedisStoreValue(Object key, Callable<T> callable) {
        T value = redisCache.get(key, callable);
        return toStoreValue(value);
    }

    @Override
    public void put(Object key, Object value) {
        if (!super.isAllowNullValues() && value == null) {
            this.evict(getKey(key));
            return;
        }

        redisCache.put(getKey(key), toStoreValue(value));
//        push(new CacheMessage(this.name, key));
        caffeineCache.put(getKey(key), value);
    }

    @Override
    public void evict(Object key) {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
       redisCache.evict(getKey(key));
       caffeineCache.evict(getKey(key));
    }

    @Override
    public void clear() {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
//        redisCache.clear();
        caffeineCache.clear();
    }

    private String getKey(Object key) {
        return this.name.concat(":").concat(StringUtils.isEmpty(cachePrefix) ? key : cachePrefix.concat(":").concat(String.valueOf(key)));
    }
}
