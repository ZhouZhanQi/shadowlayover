package com.shadowlayover.common.cache.config;

import com.shadowlayover.common.cache.props.CacheRedisCaffeineProperties;
import com.shadowlayover.common.cache.props.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-18:01
 * @desc: redisson自动装配
 * </pre>
 */
@Slf4j
@ConditionalOnClass(Redisson.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    @Order(value = 2)
    public RedissonLock redissonLock(RedissonManager redissonManager) {
        RedissonLock redissonLock = new RedissonLock();
        redissonLock.setRedissonManager(redissonManager);
        log.info("[RedissonLock]组装完毕");
        return redissonLock;
    }
    
    @Bean
    @ConditionalOnMissingBean
    @Order(value = 1)
    public RedissonManager redissonManager(RedissonProperties redissonProperties) {
        RedissonManager redissonManager =
                new RedissonManager(redissonProperties);
        log.info("[RedissonManager]组装完毕,当前连接方式:" + redissonProperties.getType() +
                ",连接地址:" + redissonProperties.getAddress());
        return redissonManager;
    }

}
