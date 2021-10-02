package com.shadowlayover.message.service;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.message.model.domain.SysNotice;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("sysNotice/{id}")
    ResponseData<SysNotice> getSysNotice(@PathVariable("id") Long id);
}
