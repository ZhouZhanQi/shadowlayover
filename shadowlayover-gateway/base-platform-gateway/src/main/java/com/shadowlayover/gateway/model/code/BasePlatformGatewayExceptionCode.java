package com.shadowlayover.gateway.model.code;

import com.shadowlayover.common.core.model.code.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/28-17:25
 * @desc: 90****
 * </pre>
 */
@Getter
@AllArgsConstructor
public enum BasePlatformGatewayExceptionCode implements BaseExceptionCode {

    TOKEN_INFO_ERROR(900001, "token令牌错误"),

    TOKEN_INFO_STORE_EMPTY(900002, "token信息为空"),

    TOKEN_INFO_EXPIRED(900003, "token信息过期"),
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
