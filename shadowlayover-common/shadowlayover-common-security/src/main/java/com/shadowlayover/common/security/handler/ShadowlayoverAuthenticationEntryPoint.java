package com.shadowlayover.common.security.handler;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.common.core.model.code.CommonResponseCode;
import com.shadowlayover.common.web.utils.ResponseUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-16:02
 * @desc: 匿名用户访问无权限处理器
 * </pre>
 */
public class ShadowlayoverAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.responseWriter(httpServletResponse, MediaType.APPLICATION_JSON_VALUE, HttpServletResponse.SC_UNAUTHORIZED, ResponseData.fail(CommonResponseCode.NO_AUTH));
    }
}
