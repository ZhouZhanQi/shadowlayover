package com.shadowlayover.message.service;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.message.model.domain.SysNotice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/27-14:43
 * @desc: 通知消息
 * </pre>
 */
public interface SysNoticeRemoteService {
    
    /**
     *
     * @param sysNotice
     * @return
     */
    @PostMapping("sysNotice")
    ResponseData<SysNotice> saveSysNotice(@RequestBody SysNotice sysNotice);
}
