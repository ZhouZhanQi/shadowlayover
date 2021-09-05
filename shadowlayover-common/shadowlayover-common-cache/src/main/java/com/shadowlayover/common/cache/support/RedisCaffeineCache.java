package com.shadowlayover.common.cache.support;

import com.github.benmanes.caffeine.cache.Cache;
import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import com.shadowlayover.common.cache.lock.RedisLock;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
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

    private RedisTemplate<String, Object> redisTemplate;

    @Getter
    private Cache<Object, Object> caffeineCache;

    private String cachePrefix;

    private long defaultExpiration = 0;

    private Map<String, Long> expires;

    private String topic = "cache:redis:caffeine:topic";

    private Map<String, ReentrantLock> keyLockMap = new ConcurrentHashMap<>();


    protected RedisCaffeineCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    public RedisCaffeineCache(String name, RedisTemplate<String, Object> redisTemplate,
                              Cache<Object, Object> caffeineCache, CacheRedisCaffeineProperties cacheRedisCaffeineProperties) {
        super(cacheRedisCaffeineProperties.isCacheNullValues());
        this.name = name;
        this.redisTemplate = redisTemplate;
        this.caffeineCache = caffeineCache;
        this.cachePrefix = cacheRedisCaffeineProperties.getCachePrefix();
        this.defaultExpiration = cacheRedisCaffeineProperties.getRedis().getDefaultExpiration();
        this.expires = cacheRedisCaffeineProperties.getRedis().getExpires();
        this.topic = cacheRedisCaffeineProperties.getRedis().getTopic();
    }

    @Override
    protected Object lookup(Object key) {
        Object cacheKey = getKey(key.toString());
        Object value = caffeineCache.getIfPresent(key);
        if(value != null) {
            log.debug(">>> 从caffeine中获取缓存, key为: {}", cacheKey);
            return value;
        }

        value = redisTemplate.opsForValue().get(cacheKey);

        if(value != null) {
            log.debug(">>> 从reids获取缓存并且放到caffeine中, key为 : {}", cacheKey);
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
        Object value = lookup(key);
        if (value != null) {
            return (T) value;
        }

        ReentrantLock lock = keyLockMap.get(key.toString());
        if (lock == null) {
            log.debug(">>> 为key创建锁: {}", key);
            lock = new ReentrantLock();
            keyLockMap.putIfAbsent(key.toString(), lock);
        }

        try {
            lock.lock();
            value = lookup(key);
            if (value != null) {
                return (T) value;
            }
            value = callable.call();
            Object storeValue = toStoreValue(value);
            put(key, storeValue);
            return (T) value;
        } catch (Exception e) {
            throw new ValueRetrievalException(key, callable, e.getCause());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (!super.isAllowNullValues() && value == null) {
            this.evict(key);
            return;
        }
        long expire = getExpire();
        if (expire > 0) {
            redisTemplate.opsForValue().set(getKey(key.toString()), toStoreValue(value), expire, TimeUnit.MILLISECONDS);
        } else {
            redisTemplate.opsForValue().set(getKey(key.toString()), toStoreValue(value));
        }

        push(new CacheMessage(this.name, key));
        caffeineCache.put(key, value);
    }


    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        Object cacheKey = getKey(key.toString());
        Object prevValue = null;
        // 考虑使用分布式锁，或者将redis的setIfAbsent改为原子性操作
        RedisLock redisLock = new RedisLock(redisTemplate, "_sync_lock");
        if (redisLock.lock()) {
            prevValue = redisTemplate.opsForValue().get(cacheKey);
            if(prevValue == null) {
                long expire = getExpire();
                if(expire > 0) {
                    redisTemplate.opsForValue().set(getKey(key.toString()), toStoreValue(value), expire, TimeUnit.MILLISECONDS);
                } else {
                    redisTemplate.opsForValue().set(getKey(key.toString()), toStoreValue(value));
                }

                push(new CacheMessage(this.name, key));
                caffeineCache.put(key, toStoreValue(value));
            }
        }
        return toValueWrapper(prevValue);
    }

    @Override
    public void evict(Object key) {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
        redisTemplate.delete(getKey(key.toString()));
        push(new CacheMessage(this.name, key));
        caffeineCache.invalidate(key);
    }

    @Override
    public void clear() {
        // 先清除redis中缓存数据，然后清除caffeine中的缓存，避免短时间内如果先清除caffeine缓存后其他请求会再从redis里加载到caffeine中
        Set<String> keys = redisTemplate.keys(this.name.concat(":*"));
        for (String key : keys) {
            redisTemplate.delete(key);
        }

        push(new CacheMessage(this.name, null));
        caffeineCache.invalidateAll();
    }

    private String getKey(String key) {
        return this.name.concat(":").concat(StringUtils.isEmpty(cachePrefix) ? key : cachePrefix.concat(":").concat(key));
    }

    private long getExpire() {
        long expire = defaultExpiration;
        Long cacheNameExpire = expires.get(this.name);
        return cacheNameExpire == null ? expire : cacheNameExpire.longValue();
    }

    private void push(CacheMessage message) {
        redisTemplate.convertAndSend(topic, message);
    }

    public void clearLocal(Object key) {
        log.debug(">>> 清空本地缓存, key为: {}", key);
        if (key == null) {
            caffeineCache.invalidateAll();
        } else {
            caffeineCache.invalidate(key);
        }
    }
}
