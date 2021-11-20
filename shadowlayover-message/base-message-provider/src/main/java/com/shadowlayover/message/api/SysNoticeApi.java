package com.shadowlayover.message.api;


import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.message.model.domain.SysNotice;
import com.shadowlayover.message.service.ISysNoticeService;
import com.shadowlayover.message.service.SysNoticeRemoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统通知 前端控制器
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SysNoticeApi implements SysNoticeRemoteService {

    private final ISysNoticeService sysNoticeService;

    @Override
    public ResponseData<SysNotice> saveSysNotice(SysNotice sysNotice) {
        sysNoticeService.saveNotice(sysNotice);
        return ResponseData.success(sysNotice);
    }

    @Override
    public ResponseData<SysNotice> saveSysNotice2(SysNotice sysNotice) {
        sysNoticeService.testNotice(sysNotice);
        return ResponseData.success(sysNotice);
    }

    //
    @Override
    public ResponseData<SysNotice> getSysNotice(Long id) {
        log.info("测试链路追踪");
        return ResponseData.success(sysNoticeService.getById(id));
    }
}

