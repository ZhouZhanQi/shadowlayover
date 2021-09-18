package com.shadowlayover.oauth.authentication.sms;

import com.shadowlayover.common.security.user.ShadowlayoverUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/14-14:24
 * @desc: 短信验证码验证提供者
 * </pre>
 */
@RequiredArgsConstructor
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    
    private final ShadowlayoverUserDetailService shadowlayoverUserDetailService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = shadowlayoverUserDetailService.loadUserByPhone((String) authentication.getPrincipal());
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authentication.getDetails());
        return authenticationResult;
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
