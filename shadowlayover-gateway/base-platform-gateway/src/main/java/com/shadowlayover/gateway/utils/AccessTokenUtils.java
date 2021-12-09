package com.shadowlayover.gateway.utils;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.gateway.model.code.BasePlatformGatewayExceptionCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Maps;
import org.springframework.web.client.RestTemplate;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.time.LocalDateTime;
import java.util.Map;

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
        AssertUtils.checkArgument(StringUtils.isBlank(headValue) || headValue.toLowerCase().startsWith(BEARER_TOKEN_PREFIX.toLowerCase()), new BusinessException(BasePlatformGatewayExceptionCode.TOKEN_INFO_ERROR));
        return headValue.replaceFirst(BEARER_TOKEN_PREFIX, "");
    }

}
