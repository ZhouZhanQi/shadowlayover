package com.shadowlayover.common.cache.layer;

import cn.hutool.core.util.ArrayUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import org.apache.commons.collections4.MapUtils;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/4-17:59
 * @desc: cafffeine本地 缓存管理
 * </pre>
 */
public class ShadowlayoverCaffeineCacheManager extends CaffeineCacheManager {
    private final CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    public ShadowlayoverCaffeineCacheManager(CacheRedisCaffeineProperties cacheRedisCaffeineProperties) {
        super(ArrayUtil.toArray(cacheRedisCaffeineProperties.getCacheNames(), String.class));
        this.cacheRedisCaffeineProperties = cacheRedisCaffeineProperties;
        this.setAllowNullValues(cacheRedisCaffeineProperties.isCacheNullValues());
        this.setCaffeine(caffeineCache());
    }


    public Caffeine<Object, Object> caffeineCache(){
        Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();
        if(cacheRedisCaffeineProperties.getCaffeine().getExpireAfterAccess() > 0) {
            cacheBuilder.expireAfterAccess(cacheRedisCaffeineProperties.getCaffeine().getExpireAfterAccess(), TimeUnit.MILLISECONDS);
        }
        if(cacheRedisCaffeineProperties.getCaffeine().getExpireAfterWrite() > 0) {
            cacheBuilder.expireAfterWrite(cacheRedisCaffeineProperties.getCaffeine().getExpireAfterWrite(), TimeUnit.MILLISECONDS);
        }
        if(cacheRedisCaffeineProperties.getCaffeine().getInitialCapacity() > 0) {
            cacheBuilder.initialCapacity(cacheRedisCaffeineProperties.getCaffeine().getInitialCapacity());
        }
        if(cacheRedisCaffeineProperties.getCaffeine().getMaximumSize() > 0) {
            cacheBuilder.maximumSize(cacheRedisCaffeineProperties.getCaffeine().getMaximumSize());
        }
        if(cacheRedisCaffeineProperties.getCaffeine().getRefreshAfterWrite() > 0) {
            cacheBuilder.refreshAfterWrite(cacheRedisCaffeineProperties.getCaffeine().getRefreshAfterWrite(), TimeUnit.MILLISECONDS);
        }
        return cacheBuilder;
    }


    @Override
    protected Cache<Object, Object> createNativeCaffeineCache(String name) {
        Map<String, Long> expires = cacheRedisCaffeineProperties.getRedis().getExpires();
        if (MapUtils.isNotEmpty(expires)) {
            if (expires.containsKey(name)) {
                Long timeExpire = expires.get(name);
                return Caffeine.newBuilder().expireAfterWrite(timeExpire, TimeUnit.MILLISECONDS).build();
            }
        }
        return super.createNativeCaffeineCache(name);
    }
}
