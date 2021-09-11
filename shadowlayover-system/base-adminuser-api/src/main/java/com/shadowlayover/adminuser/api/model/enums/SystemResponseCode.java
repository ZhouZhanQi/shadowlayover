package com.shadowlayover.adminuser.api.model.enums;

import com.shadowlayover.common.core.model.code.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-14:30
 * @desc: 用户中心响应编码枚举
 * code 10****
 *
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum SystemResponseCode implements BaseExceptionCode {
    
    
    
    ;
    private final int code;
    
    private final String message;
    
    @Override
    public int getCode() {
        return this.code;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
}
