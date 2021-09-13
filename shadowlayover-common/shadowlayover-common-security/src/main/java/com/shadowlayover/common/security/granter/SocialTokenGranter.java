package com.shadowlayover.common.security.granter;


import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 1:56 下午
 * @desc 社交登录TokenGranter
 */
public class SocialTokenGranter implements TokenGranter {
    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        return null;
    }
}
