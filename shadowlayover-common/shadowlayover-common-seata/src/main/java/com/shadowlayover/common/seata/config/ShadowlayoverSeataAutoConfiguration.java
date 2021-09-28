package com.shadowlayover.common.seata.config;

import com.shadowlayover.common.core.factory.YamlPropertySourceFactory;
import com.shadowlayover.common.seata.props.ShadowlayoverSeataProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/28-18:30
 * @desc: seata自动配置
 * </pre>
 */
@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:shadowlayover-seata.yml")
@EnableConfigurationProperties(ShadowlayoverSeataProperties.class)
public class ShadowlayoverSeataAutoConfiguration {
}
