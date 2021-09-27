package com.shadowlayover.component.service.impl;

import com.shadowlayover.component.model.domain.OperateLog;
import com.shadowlayover.component.mapper.OperateLogMapper;
import com.shadowlayover.component.service.IOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements IOperateLogService {

}
