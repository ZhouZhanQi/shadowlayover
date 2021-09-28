package com.shadowlayover.component.service.impl;

import com.shadowlayover.component.model.domain.SysConfig;
import com.shadowlayover.component.mapper.SysConfigMapper;
import com.shadowlayover.component.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadowlayover.message.client.SysNoticeRemoteClient;
import com.shadowlayover.message.model.domain.SysNotice;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@RequiredArgsConstructor
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {
    
    private final SysNoticeRemoteClient sysNoticeRemoteClient;
    
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void testSeata() {
        SysConfig config = new SysConfig();
        config.setConfigType(1);
        config.setConfigKey("test");
        config.setConfigValue("test");
        this.save(config);
        SysNotice notice = new SysNotice();
        notice.setNoticeType(1);
        notice.setNoticeTitle("test");
        notice.setNoticeContent("test");
        sysNoticeRemoteClient.saveSysNotice(notice);
    }
}
