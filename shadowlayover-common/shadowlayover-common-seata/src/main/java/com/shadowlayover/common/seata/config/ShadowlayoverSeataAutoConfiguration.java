package com.shadowlayover.common.seata.config;

import com.shadowlayover.common.core.factory.YamlPropertySourceFactory;
import com.shadowlayover.common.seata.props.ShadowlayoverSeataProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@EnableConfigurationProperties(ShadowlayoverSeataProperties.class)
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:shadowlayover-seata.yml")
@Configuration
public class ShadowlayoverSeataAutoConfiguration implements InitializingBean {
    
    @Autowired
    private ShadowlayoverSeataProperties shadowlayoverSeataProperties;
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("shadowlayover seata init applicationId: {}, txServiceGroup: {} ...", shadowlayoverSeataProperties.getApplicationId(), shadowlayoverSeataProperties.getTxServiceGroup());
    }
}
