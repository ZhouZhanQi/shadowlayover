package com.shadowlayover.oauth.model.convert;

import com.shadowlayover.oauth.model.bo.SysUserBo;
import com.shadowlayover.oauth.model.domain.SysUser;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-20:02
 * @desc: 用户信息转换类
 * </pre>
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysUserConvert {
    
    SysUserConvert INSTANCE = Mappers.getMapper( SysUserConvert.class );
    
    /**
     * 用户信息转换
     * @param sysUser
     * @return
     */
    @Mapping(target = "password", ignore = true)
    SysUserBo convert2Bo(SysUser sysUser);
    
    /**
     * 更新信息
     * @param source
     * @param target
     */
    void updateBo(SysUserBo source, @MappingTarget SysUserBo target);
}
