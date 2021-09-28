package com.shadowlayover.common.seata.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/28-18:28
 * @desc: seata配置
 * </pre>
 */
@Data
@ConfigurationProperties(prefix = "shadowlayover.seata")
public class ShadowlayoverSeataProperties {
    
    private String applicationId;
    
    private String txServiceGroup;
}
