package com.shadowlayover.common.web.properties;

import lombok.Data;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/30-15:35
 * @desc: xxladmin配置
 * </pre>
 */
@Data
public class XxlAdminProperties {
    
    /**
     * 调度中心部署跟地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。 执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
     */
    private String addresses;
}
