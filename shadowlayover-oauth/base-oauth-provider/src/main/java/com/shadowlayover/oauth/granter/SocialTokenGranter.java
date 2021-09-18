package com.shadowlayover.oauth.granter;


import com.google.common.collect.Maps;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 1:56 下午
 * @desc 社交登录TokenGranter
 */
public class SocialTokenGranter extends AbstractTokenGranter {
    
    private final AuthenticationManager authenticationManager;
    
    protected SocialTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameterMap = Maps.newLinkedHashMap(tokenRequest.getRequestParameters());
        return null;
    }
}
