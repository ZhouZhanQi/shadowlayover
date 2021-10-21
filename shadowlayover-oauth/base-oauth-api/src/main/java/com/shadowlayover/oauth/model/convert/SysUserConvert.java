package com.shadowlayover.oauth.model.convert;

import com.shadowlayover.common.core.convert.IBaseConvert;
import com.shadowlayover.oauth.model.bo.SysUserBo;
import com.shadowlayover.oauth.model.domain.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/21-20:02
 * @desc: 用户信息转换类
 * </pre>
 */
@Mapper(componentModel = "spring")
public interface SysUserConvert extends IBaseConvert {
    
    /**
     * 用户信息转换
     * @param sysUser
     * @return
     */
    @Mapping(target = "password", ignore = true)
    SysUserBo convert2Bo(SysUser sysUser);
}
