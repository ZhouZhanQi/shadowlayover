package com.shadowlayover.component.service;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.component.model.domain.SysConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/27-11:17
 * @desc: 系统配置API
 * </pre>
 */
public interface SysConfigRemoteService {
    
    /**
     * 保存系统配置信息Sy
     * @param sysConfig
     * @return
     */
    @PostMapping("/sysConfig")
    ResponseData<SysConfig> saveSysConfig(@RequestBody SysConfig sysConfig);
}
