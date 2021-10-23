package com.shadowlayover.oauth.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.shadowlayover.oauth.model.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    
    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from sys_user ${ew.customSqlSegment}")
    SysUser selectOneIgnoreTenant(@Param("ew") Wrapper<SysUser> queryWrapper);
}
