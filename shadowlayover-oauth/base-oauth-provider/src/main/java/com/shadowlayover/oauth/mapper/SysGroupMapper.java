package com.shadowlayover.oauth.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.shadowlayover.oauth.model.domain.SysGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户组 Mapper 接口
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Mapper
public interface SysGroupMapper extends BaseMapper<SysGroup> {

    @InterceptorIgnore(tenantLine = "true")
    SysGroup selectOneIgnoreTenant();
}
