package com.shadowlayover.common.security.handler;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.common.web.utils.ResponseUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-15:55
 * @desc: 认证过的用户无权限访问异常
 * </pre>
 */
public class ShadowlayoverAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.responseWriter(httpServletResponse, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_FORBIDDEN, ResponseData.fail("没有访问权限"));
    }

//    @Override
//    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
//        return ResponseUtils.webFluxResponseWriter(serverWebExchange.getResponse(), MediaType.APPLICATION_JSON_VALUE,  HttpStatus.FORBIDDEN, ResponseData.fail("没有访问权限"));
//    }
}
