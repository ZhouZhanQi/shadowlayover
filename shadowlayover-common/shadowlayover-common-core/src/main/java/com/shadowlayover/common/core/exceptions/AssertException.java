package com.shadowlayover.common.core.exceptions;

import cn.hutool.core.util.StrUtil;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/20-17:36
 * @desc: 断言异常
 * </pre>
 */
public class AssertException extends RuntimeException {
    
    private static final long serialVersionUID = 6394284648244311928L;
    
    public AssertException() {
    }
    
    public AssertException(String message) {
        super(message);
    }
    
    public AssertException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AssertException(Throwable cause) {
        super(cause);
    }
    
    public AssertException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }
    
    public AssertException(Throwable cause, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), cause);
    }
}
