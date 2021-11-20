package com.shadowlayover.message.service.impl;

import com.shadowlayover.common.core.exceptions.BaseException;
import com.shadowlayover.message.client.ExampleOrderClient;
import com.shadowlayover.message.model.domain.SysNotice;
import com.shadowlayover.message.mapper.SysNoticeMapper;
import com.shadowlayover.message.service.ISysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统通知 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@RequiredArgsConstructor
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {

    private final ExampleOrderClient exampleOrderClient;

    @Override
    public SysNotice saveNotice(SysNotice sysNotice) {
        sysNotice = new SysNotice();
        sysNotice.setNoticeTitle("aaa");
        sysNotice.setNoticeContent("aaa");
        sysNotice.setNoticeType(1);
        sysNotice.setStatus(1);
        this.save(sysNotice);

        exampleOrderClient.testOrder();
        return sysNotice;
//        return sysNotice;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysNotice testNotice(SysNotice sysNotice) {
        sysNotice = new SysNotice();
        sysNotice.setNoticeTitle("bbb");
        sysNotice.setNoticeContent("bbb");
        sysNotice.setNoticeType(1);
        sysNotice.setStatus(1);
        this.save(sysNotice);
        exampleOrderClient.testOrder();
        return sysNotice;
    }
}
