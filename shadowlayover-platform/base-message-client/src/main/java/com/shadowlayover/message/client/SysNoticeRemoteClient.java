package com.shadowlayover.message.client;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.message.client.fallback.SysNoticeRemoteFallback;
import com.shadowlayover.message.config.MessageFeignConfiguration;
import com.shadowlayover.message.model.constants.MessageConstants;
import com.shadowlayover.message.model.domain.SysNotice;
import com.shadowlayover.message.service.SysNoticeRemoteService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/27-14:43
 * @desc: 通知消息
 * </pre>
 */
@FeignClient(value = MessageConstants.SERVICE_NAME, fallbackFactory = SysNoticeRemoteFallback.class, configuration = MessageFeignConfiguration.class)
public interface SysNoticeRemoteClient extends SysNoticeRemoteService {

    

}
