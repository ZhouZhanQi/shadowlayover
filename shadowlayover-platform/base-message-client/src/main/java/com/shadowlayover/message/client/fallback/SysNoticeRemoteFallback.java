package com.shadowlayover.message.client.fallback;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.message.client.SysNoticeRemoteClient;
import com.shadowlayover.message.model.domain.SysNotice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-11:29
 * @desc:
 * </pre>
 */
@Slf4j
public class SysNoticeRemoteFallback implements FallbackFactory<SysNoticeRemoteClient> {
    
    @Override
    public SysNoticeRemoteClient create(Throwable cause) {
        log.error(">>> 服务熔断: {}", cause);
        return new SysNoticeRemoteClient() {
            @Override
            public ResponseData<SysNotice> saveSysNotice(SysNotice sysNotice) {
                //服务降级处理
                return null;
            }
        };
    }
}
