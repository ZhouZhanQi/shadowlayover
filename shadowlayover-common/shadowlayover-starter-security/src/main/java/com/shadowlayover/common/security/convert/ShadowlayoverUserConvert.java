package com.shadowlayover.common.security.convert;

import com.shadowlayover.common.core.model.LoginUser;
import com.shadowlayover.common.security.user.ShadowlayoverUser;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/30-18:05
 * @desc: 平台用户转换器
 * </pre>
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface ShadowlayoverUserConvert {

    ShadowlayoverUserConvert INSTANCE = Mappers.getMapper( ShadowlayoverUserConvert.class );

    /**
     * 用户转换
     * @param shadowlayoverUser
     * @return
     */
    LoginUser convertToTokenUser(ShadowlayoverUser shadowlayoverUser);
}
