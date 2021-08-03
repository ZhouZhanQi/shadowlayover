package com.shadowlayover.common.cache.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/3-10:05
 * @desc: 缓存消息监听
 * </pre>
 */
@Slf4j
public class CacheMessageListener implements MessageListener {
    
    private RedisTemplate<String, Object> redisTemplate;
    
    private RedisCaffeineCacheManager redisCaffeineCacheManager;
    
    public CacheMessageListener(RedisTemplate<String, Object> redisTemplate,
                                RedisCaffeineCacheManager redisCaffeineCacheManager) {
        super();
        this.redisTemplate = redisTemplate;
        this.redisCaffeineCacheManager = redisCaffeineCacheManager;
    }
    
    @Override
    public void onMessage(Message message, byte[] bytes) {
        CacheMessage cacheMessage = (CacheMessage) redisTemplate.getValueSerializer().deserialize(message.getBody());
        log.debug(">>> recevice a redis topic message, clear local cache, the cacheName is {}, the key is {}", cacheMessage.getCacheName(), cacheMessage.getKey());
        redisCaffeineCacheManager.clearLocal(cacheMessage.getCacheName(), cacheMessage.getKey());
    }
}
