package com.shadowlayover.oauth.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.shadowlayover.oauth.model.domain.SysGroup;
import com.shadowlayover.oauth.model.domain.SysPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 系统租户职位 Mapper 接口
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {

    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from sys_post ${ew.customSqlSegment}")
    SysPost selectOneIgnoreTenant(@Param("ew") Wrapper<SysPost> queryWrapper);
}
