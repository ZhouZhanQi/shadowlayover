package com.shadowlayover.common.cache.model.code;

import com.shadowlayover.common.core.model.code.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-14:30
 * @desc: 缓存包异常编码
 * code 3***
 *
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum CacheExceptionCode implements BaseExceptionCode {
    
    REDISSON_CONNECT_TYPE_ERROR(3001, "redisson连接类型错误"),
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
