package com.shadowlayover.component.service;

import com.shadowlayover.component.model.domain.SysConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统配置 服务类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
public interface ISysConfigService extends IService<SysConfig> {

    public void testSeata();
    
}
