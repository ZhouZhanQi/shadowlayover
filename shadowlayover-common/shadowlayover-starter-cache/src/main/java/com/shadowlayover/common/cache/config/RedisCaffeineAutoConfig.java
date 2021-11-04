package com.shadowlayover.common.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.shadowlayover.common.cache.RedisCacheHelper;
import com.shadowlayover.common.cache.layer.ShadowlayoverCacheManager;
import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import com.shadowlayover.common.cache.support.CacheMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/31-17:18
 * @desc: redis配置
 * </pre>
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CacheRedisCaffeineProperties.class)
@Import(RedisConfiguration.class)
public class RedisCaffeineAutoConfig {

    @Autowired
    private CacheRedisCaffeineProperties cacheRedisCaffeineProperties;

    @Bean
    public ShadowlayoverCacheManager cacheManager(CaffeineCacheManager caffeineCacheManager, RedisCacheManager redisCacheManager) {
        ShadowlayoverCacheManager cacheManager = new ShadowlayoverCacheManager();
        cacheManager.setCaffeineCacheManager(caffeineCacheManager);
        cacheManager.setRedisCacheManager(redisCacheManager);
        cacheManager.setCacheRedisCaffeineProperties(cacheRedisCaffeineProperties);
        return cacheManager;
    }



}
