package com.shadowlayover.common.web.handler;

import com.shadowlayover.common.core.exceptions.BaseException;
import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.common.core.model.code.CommonExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/3-14:05
 * @desc: 全局异常处理
 * </pre>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BaseException.class)
    public ResponseData<?> handlerBaseException(BaseException e) {
        log.error(">>> shadowlayover base exception", e);
        return ResponseData.fail(e.getCode(), e.getMessage());
    }
}
