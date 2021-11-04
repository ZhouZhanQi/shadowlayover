package com.shadowlayover.common.security.handler;

import cn.hutool.http.ContentType;
import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.common.web.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-16:08
 * @desc: 认证失败处理器
 * </pre>
 */
public class ShadowlayoverAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.responseWriter(httpServletResponse, ContentType.JSON.getValue(), HttpStatus.UNAUTHORIZED.value(), ResponseData.fail("未授权"));
    }
}
