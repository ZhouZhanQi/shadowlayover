package com.shadowlayover.message.service.impl;

import com.shadowlayover.common.core.exceptions.BaseException;
import com.shadowlayover.message.model.domain.SysNotice;
import com.shadowlayover.message.mapper.SysNoticeMapper;
import com.shadowlayover.message.service.ISysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements ISysNoticeService {

    @Override
    public SysNotice saveNotice(SysNotice sysNotice) {
        sysNotice = new SysNotice();
        sysNotice.setNoticeTitle("aaa");
        sysNotice.setNoticeContent("aaa");
        sysNotice.setNoticeType(1);
        sysNotice.setStatus(1);
        this.save(sysNotice);
        throw new BaseException("22222");
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
        return sysNotice;
    }
}
