package com.shadowlayover.common.rocketmq.config;

import com.shadowlayover.common.core.factory.YamlPropertySourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/16-19:35
 * @desc: rocket自动配置
 * </pre>
 */
@Slf4j
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:shadowlayover-rocketmq.yml")
@Configuration(proxyBeanMethods = false)
public class ShadowlayoverRocketMqAutoConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
