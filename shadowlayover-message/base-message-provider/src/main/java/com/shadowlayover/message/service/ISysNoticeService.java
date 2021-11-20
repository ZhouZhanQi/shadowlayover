package com.shadowlayover.message.service;

import com.shadowlayover.message.model.domain.SysNotice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统通知 服务类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
public interface ISysNoticeService extends IService<SysNotice> {
    
    /**
     * 保存信息
     * @param sysNotice
     * @return
     */
    SysNotice saveNotice(SysNotice sysNotice);

    SysNotice testNotice(SysNotice sysNotice);
    
}
