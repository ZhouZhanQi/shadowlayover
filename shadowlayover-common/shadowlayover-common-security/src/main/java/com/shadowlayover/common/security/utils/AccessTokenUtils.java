package com.shadowlayover.common.security.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.common.security.model.code.SecurityExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.time.LocalDateTime;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/10/28-17:13
 * @desc: 令牌工具类
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenUtils {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    /**
     * 获取token
     * @param headValue
     * @return
     */
    public static String getTokenFromHead(String headValue) {
        AssertUtils.checkArgument(StringUtils.isBlank(headValue) || headValue.toLowerCase().startsWith(BEARER_TOKEN_PREFIX.toLowerCase()), new BusinessException(SecurityExceptionCode.TOKEN_INFO_ERROR));
        return headValue.replaceFirst(BEARER_TOKEN_PREFIX, "");
    }

    /**
     * 校验token
     * @param tokenStore
     * @param headValue
     */
    public static void checkTokenFromHead(TokenStore tokenStore, String headValue) {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(AccessTokenUtils.getTokenFromHead(getTokenFromHead(headValue)));
        AssertUtils.checkNotNull(oAuth2AccessToken, new BusinessException(""));
        AssertUtils.checkArgument(LocalDateTime.now().isAfter(LocalDateTimeUtil.of(oAuth2AccessToken.getExpiration())), new BusinessException(SecurityExceptionCode.TOKEN_INFO_EXPIRED));
    }
}
