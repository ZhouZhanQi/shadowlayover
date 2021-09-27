package com.shadowlayover.component.mapper;

import com.shadowlayover.component.model.domain.OperateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统日志 Mapper 接口
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-09-27
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {

}
