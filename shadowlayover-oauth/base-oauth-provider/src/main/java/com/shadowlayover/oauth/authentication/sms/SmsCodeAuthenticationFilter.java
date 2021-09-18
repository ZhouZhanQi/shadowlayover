package com.shadowlayover.oauth.authentication.sms;

import com.shadowlayover.oauth.model.constants.BaseOauthConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/14-13:48
 * @desc: 短信验证码过滤器
 * </pre>
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    
    
    private boolean postOnly = true;
    
    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher(BaseOauthConstant.OAUTH_MOBILE, HttpMethod.POST.name()));
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !HttpMethod.POST.name().equals(httpServletRequest.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + httpServletRequest.getMethod());
        }
    
        // 获取请求中的参数值
        String mobile = httpServletRequest.getParameter(BaseOauthConstant.DEFAULT_PARAMETER_NAME_MOBILE);
        if (StringUtils.isBlank(mobile)) {
            mobile = "";
        }
        mobile = mobile.trim();
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
        // Allow subclasses to set the "details" property
        setDetails(httpServletRequest, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
    
    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
