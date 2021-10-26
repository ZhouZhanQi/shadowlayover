package com.shadowlayover.oauth.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.shadowlayover.oauth.model.domain.SysTenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统租户 Mapper 接口
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@InterceptorIgnore(tenantLine = "true")
@Mapper
public interface SysTenantMapper extends BaseMapper<SysTenant> {

}
