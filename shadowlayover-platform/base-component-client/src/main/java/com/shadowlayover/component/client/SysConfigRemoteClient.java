package com.shadowlayover.component.client;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.component.service.SysConfigRemoteService;
import com.shadowlayover.component.model.constants.ComponentConstants;
import com.shadowlayover.component.model.domain.SysConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/27-11:15
 * @desc: 系统配置远程调用客户端
 * </pre>
 */
@FeignClient(value = ComponentConstants.SERVICE_NAME, fallbackFactory = SysConfigRemoteClient.HystrixClientFallback.class)
public interface SysConfigRemoteClient extends SysConfigRemoteService {

    @Component("sysConfigFallback")
    class HystrixClientFallback implements SysConfigRemoteClient {
    
        @Override
        public ResponseData<SysConfig> saveSysConfig(SysConfig sysConfig) {
            return null;
        }
    }
}
