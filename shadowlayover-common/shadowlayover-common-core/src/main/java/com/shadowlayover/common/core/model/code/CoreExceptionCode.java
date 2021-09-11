package com.shadowlayover.common.core.model.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-14:10
 * @desc: 基础响应信息
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum CoreExceptionCode implements BaseExceptionCode {
    
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    
    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),
    
    /**
     * 无访问权限
     */
    NO_AUTH(401, "无访问权限"),
    
    /**
     * 服务未找到
     */
    NOT_FOUND(404, "服务未找到"),
    
    /**
     * 请求频繁
     */
    TOO_MANY_REQUESTS(429, "请求频繁"),
    
    /**
     * 服务异常
     */
    ERROR(500, "服务异常"),
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
