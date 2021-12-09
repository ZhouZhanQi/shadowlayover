package com.shadowlayover.gateway.props;

import lombok.Data;
import org.assertj.core.util.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-15:12
 * @desc: 安全配置
 * </pre>
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "shadowlayover.gateway")
@Component
public class ShadowlayoverGatewayProperties {
    
    /**
     * 需要登录，不需要校验权限
     */
    private List<String> publicUrls = Lists.newArrayList();
    
    /**
     * 不需要登录，不校验权限
     */
    private List<String> ignoreUrls = Lists.newArrayList();
}
