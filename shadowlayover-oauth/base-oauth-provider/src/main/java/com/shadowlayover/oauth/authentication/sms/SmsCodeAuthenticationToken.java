package com.shadowlayover.oauth.authentication.sms;

import com.shadowlayover.common.core.exceptions.FrameworkException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/14-11:38
 * @desc: 短信验证码token信息
 * </pre>
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {
    
    private final Object principal;
    
    public SmsCodeAuthenticationToken(String mobilePhone) {
        super(null);
        this.principal = mobilePhone;
        setAuthenticated(false);
    }
    
    public SmsCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }
    
    @Override
    public Object getCredentials() {
        return this.principal;
    }
    
    @Override
    public Object getPrincipal() {
        return this.principal;
    }
    
    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new FrameworkException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }
    
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
