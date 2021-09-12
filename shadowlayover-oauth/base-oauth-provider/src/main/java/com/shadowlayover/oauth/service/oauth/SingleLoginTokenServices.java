package com.shadowlayover.oauth.service.oauth;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.*;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 3:18 上午
 * @desc 重写DefaultTokenServices, 实现同应用登录账号互踢
 */
public class SingleLoginTokenServices extends DefaultTokenServices {

    private TokenStore tokenStore;

    private TokenEnhancer tokenEnhancer;

    /**
     * 是否登录同应用同账号互踢
     */
    private boolean isSingleLogin;

    public SingleLoginTokenServices(boolean isSingleLogin) {
        this.isSingleLogin = isSingleLogin;
    }

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken existingToken = tokenStore.getAccessToken(authentication);
        OAuth2RefreshToken refreshToken = null;
        if (Objects.nonNull(existingToken)) {
            if (isSingleLogin) {
                tokenStore.removeAccessToken(existingToken);
                Optional.ofNullable(existingToken.getRefreshToken()).ifPresent(accessToken -> tokenStore.removeRefreshToken(accessToken));
            } else if (existingToken.isExpired()) {
                if (existingToken.getRefreshToken() != null) {
                    refreshToken = existingToken.getRefreshToken();
                    // The token store could remove the refresh token when the
                    // access token is removed, but we want to
                    // be sure...
                    tokenStore.removeRefreshToken(refreshToken);
                }
                tokenStore.removeAccessToken(existingToken);
            } else {
                // Re-store the access token in case the authentication has changed
                tokenStore.storeAccessToken(existingToken, authentication);
                return existingToken;
            }
        };


        // Only create a new refresh token if there wasn't an existing one
        // associated with an expired access token.
        // Clients might be holding existing refresh tokens, so we re-use it in
        // the case that the old access token
        // expired.
        if (refreshToken == null) {
            refreshToken = createRefreshToken(authentication);
        // But the refresh token itself might need to be re-issued if it has
        // expired.
        } else if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
            ExpiringOAuth2RefreshToken expiring = (ExpiringOAuth2RefreshToken) refreshToken;
            if (LocalDateTimeUtil.toEpochMilli(LocalDateTime.now()) > expiring.getExpiration().getTime()) {
                refreshToken = createRefreshToken(authentication);
            }
        }

        OAuth2AccessToken accessToken = createAccessToken(authentication, refreshToken);
        tokenStore.storeAccessToken(accessToken, authentication);
        // In case it was modified
        refreshToken = accessToken.getRefreshToken();
        if (refreshToken != null) {
            tokenStore.storeRefreshToken(refreshToken, authentication);
        }
        return accessToken;
    }

    private OAuth2RefreshToken createRefreshToken(OAuth2Authentication authentication) {
        if (!isSupportRefreshToken(authentication.getOAuth2Request())) {
            return null;
        }
        int validitySeconds = getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
        String value = UUID.randomUUID().toString();
        if (validitySeconds > 0) {
            return new DefaultExpiringOAuth2RefreshToken(value, new Date(LocalDateTimeUtil.toEpochMilli(LocalDateTime.now())
                    + (validitySeconds * 1000L)));
        }
        return new DefaultOAuth2RefreshToken(value);
    }

    private OAuth2AccessToken createAccessToken(OAuth2Authentication authentication, OAuth2RefreshToken refreshToken) {
        DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
        int validitySeconds = getAccessTokenValiditySeconds(authentication.getOAuth2Request());
        if (validitySeconds > 0) {
            token.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
        }
        token.setRefreshToken(refreshToken);
        token.setScope(authentication.getOAuth2Request().getScope());
        return tokenEnhancer != null ? tokenEnhancer.enhance(token, authentication) : token;
    }

    @Override
    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
        super.setTokenStore(tokenStore);
    }

    @Override
    public void setTokenEnhancer(TokenEnhancer accessTokenEnhancer) {
        this.tokenEnhancer = accessTokenEnhancer;
        super.setTokenEnhancer(accessTokenEnhancer);
    }
}
