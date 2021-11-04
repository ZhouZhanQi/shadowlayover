package com.shadowlayover.common.security.model.code;

import com.shadowlayover.common.core.model.code.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/28-17:25
 * @desc: 70***
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum SecurityExceptionCode implements BaseExceptionCode {

    TOKEN_INFO_ERROR(70001, "token令牌错误"),
    TOKEN_INFO_STORE_EMPTY(70002, "token信息为空"),
    TOKEN_INFO_EXPIRED(70003, "token信息过期"),
    ;

    private final int code;

    private final String message;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
