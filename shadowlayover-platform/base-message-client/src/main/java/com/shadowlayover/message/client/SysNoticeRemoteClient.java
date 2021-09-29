package com.shadowlayover.message.client;

import com.shadowlayover.common.feign.fallback.ShadowlayoverFallbackFactory;
import com.shadowlayover.message.model.constants.MessageConstants;
import com.shadowlayover.message.service.SysNoticeRemoteService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/27-14:43
 * @desc: 通知消息
 * </pre>
 */
@FeignClient(value = MessageConstants.SERVICE_NAME, path = "/base-message")
public interface SysNoticeRemoteClient extends SysNoticeRemoteService {

}
