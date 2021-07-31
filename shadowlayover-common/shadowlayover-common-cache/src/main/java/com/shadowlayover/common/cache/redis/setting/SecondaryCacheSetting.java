package com.shadowlayover.common.cache.redis.setting;

import lombok.Builder;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/31-17:09
 * @desc: 二级缓存配置
 * </pre>
 */
@Getter
@Builder
public class SecondaryCacheSetting {
    
    /**
     * 缓存有效时间
     */
    private long expirationSecondTime;
    
    /**
     * 缓存主动在失效前强制刷新缓存的时间
     * 单位：秒
     */
    private long preloadSecondTime = 0;
    
    /**
     * 是否使用二级缓存，默认是true
     */
    private boolean usedFirstCache = true;
    
    /**
     * 是否使用强刷新（走数据库），默认是false
     */
    private boolean forceRefresh = false;
}
