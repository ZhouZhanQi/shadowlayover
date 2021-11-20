package com.shadowlayover.component.api;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.component.model.domain.SysConfig;
import com.shadowlayover.component.service.ISysConfigService;
import com.shadowlayover.component.service.SysConfigRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/27-11:31
 * @desc: 系统配置API
 * </pre>
 */
@RestController
@RequiredArgsConstructor
public class SysConfigApi implements SysConfigRemoteService {
    
    private final ISysConfigService sysConfigService;
    
    @Override
    public ResponseData<SysConfig> saveSysConfig(@RequestBody SysConfig sysConfig) {
        sysConfigService.testSeata();
        return ResponseData.success();
    }
}
