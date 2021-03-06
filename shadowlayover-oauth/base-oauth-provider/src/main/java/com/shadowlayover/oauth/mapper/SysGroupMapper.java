package com.shadowlayover.oauth.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.shadowlayover.oauth.model.domain.SysGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 系统用户组忽略租户查询
     * @param queryWrapper
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from sys_group ${ew.customSqlSegment}")
    SysGroup selectOneIgnoreTenant(@Param("ew") Wrapper<SysGroup> queryWrapper);
}
