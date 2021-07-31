package com.shadowlayover.common.cache.layering;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.concurrent.Callable;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/31-16:53
 * @desc: 多级缓存
 * </pre>
 */
@Slf4j
public class LayeringCache extends AbstractValueAdaptingCache {
    
    /**
     * 缓存的名称
     */
    private final String name;
    
    /**
     * 是否使用一级缓存
     */
    private boolean usedFirstCache = true;
    
    /**
     * redi缓存
     */
    private final CustomizedRedisCacheConfig redisCache;
    
    /**
     * Caffeine缓存
     */
    private final CaffeineCache caffeineCache;
    
    
    public LayeringCache(boolean allowNullValues, String name, boolean usedFirstCache,
                         CustomizedRedisCacheConfig redisCache,
                         CaffeineCache caffeineCache) {
        super(allowNullValues);
        this.name = name;
        this.usedFirstCache = usedFirstCache;
        this.redisCache = redisCache;
        this.caffeineCache = caffeineCache;
    }
    
    @Override
    protected Object lookup(Object o) {
        return null;
    }
    
    @Override
    public String getName() {
        return null;
    }
    
    @Override
    public Object getNativeCache() {
        return null;
    }
    
    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }
    
    @Override
    public void put(Object o, Object o1) {
    
    }
    
    @Override
    public void evict(Object o) {
    
    }
    
    @Override
    public void clear() {
    
    }
}
