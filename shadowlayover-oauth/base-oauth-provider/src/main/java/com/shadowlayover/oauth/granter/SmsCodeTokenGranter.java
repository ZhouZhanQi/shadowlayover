package com.shadowlayover.oauth.granter;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.shadowlayover.common.cache.RedisCacheHelper;
import com.shadowlayover.common.cache.model.code.RedisKeyPrefix;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.oauth.authentication.sms.SmsCodeAuthenticationToken;
import com.shadowlayover.oauth.model.code.OauthResponseCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/14-11:30
 * @desc: 短信验证码登录TokenGranter
 * </pre>
 */
public class SmsCodeTokenGranter extends AbstractTokenGranter {
    
    private final AuthenticationManager authenticationManager;
    
    private RedisCacheHelper redisCacheHelper;
    
    public SmsCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType, RedisCacheHelper redisCacheHelper) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, grantType);
        this.redisCacheHelper = redisCacheHelper;
    }
    
    public SmsCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }
    
    
    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameterMap = Maps.newLinkedHashMap(tokenRequest.getRequestParameters());
        String mobile = parameterMap.get("mobilePhone");
        String code = parameterMap.get("code");
        if (StrUtil.isBlank(code)) {
            throw new BusinessException(OauthResponseCode.SMS_CODE_AUTH_PARAM_EMPTY);
        }
        
        //验证码过期
        String smsCodeFromCache = redisCacheHelper.get(RedisKeyPrefix.OAUTH_SMS_CODE, mobile).toString();
        if (StrUtil.isBlank(smsCodeFromCache)) {
            throw new BusinessException(OauthResponseCode.SMS_CODE_AUTH_HAS_EXPIRED);
        }
        
        // 比较输入的验证码是否正确
        if (!StrUtil.equalsIgnoreCase(code, smsCodeFromCache)) {
            throw new BusinessException(OauthResponseCode.SMS_CODE_AUTH_PARAM_ERROR);
        }
    
        //删除缓存
        redisCacheHelper.delete(RedisKeyPrefix.OAUTH_SMS_CODE, mobile);
        Authentication userAuth = new SmsCodeAuthenticationToken(mobile);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameterMap);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        //If the username/password are wrong the spec says we should send 400/invalid grant
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + mobile);
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
