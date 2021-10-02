package com.shadowlayover.message.client;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.message.model.constants.MessageConstants;
import com.shadowlayover.message.model.domain.SysNotice;
import com.shadowlayover.message.service.SysNoticeRemoteService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/27-14:43
 * @desc: 通知消息
 * </pre>
 */
@FeignClient(value = MessageConstants.SERVICE_NAME, path = "/base-message", fallbackFactory = SysNoticeRemoteClient.SysNoticeRemoteFallback.class)
public interface SysNoticeRemoteClient extends SysNoticeRemoteService {

    class SysNoticeRemoteFallback implements FallbackFactory<SysNoticeRemoteClient> {

        @Override
        public SysNoticeRemoteClient create(Throwable cause) {
            return new SysNoticeRemoteClient() {
                @Override
                public ResponseData<SysNotice> saveSysNotice(SysNotice sysNotice) {
                    return null;
                }

                @Override
                public ResponseData<SysNotice> getSysNotice(Long id) {
                    return null;
                }


            };
        }
    }

}
