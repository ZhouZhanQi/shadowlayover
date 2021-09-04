package com.shadowlayover.common.core.exceptions;

import cn.hutool.core.util.StrUtil;
import com.shadowlayover.common.core.model.code.BaseExceptionCode;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-14:39
 * @desc: 业务异常
 * </pre>
 */
public class BusinessException extends BaseException {
    
    private static final long serialVersionUID = -850094070297818746L;
    
    public BusinessException() {
    }
    
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BusinessException(Throwable cause) {
        super(cause);
    }
    
    public BusinessException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }
    
    public BusinessException(Throwable cause, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), cause);
    }
    
    public <R extends BaseExceptionCode> BusinessException(R responseCode) {
        super(responseCode);
    }
}
