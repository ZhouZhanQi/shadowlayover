package com.shadowlayover.gateway.component;

import com.shadowlayover.common.core.exceptions.BaseException;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.common.core.utils.ClientUtils;
import com.shadowlayover.gateway.clients.BaseOauthTokenRemoteClient;
import com.shadowlayover.gateway.model.code.BasePlatformGatewayExceptionCode;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/30-14:43
 * @desc: 令牌认证组件
 * </pre>
 */
@Component
@RequiredArgsConstructor
public class OauthTokenComponent {

    private final BaseOauthTokenRemoteClient baseOauthTokenRemoteClient;

    /**
     * 校验token
     * @param token
     */
    public void checkToken(String token) {

        Map<String, Object> checkTokenMap = baseOauthTokenRemoteClient.checkToken(Maps.newHashMap("token", token));
        boolean isActive = (boolean) checkTokenMap.getOrDefault("isActive", false);
        AssertUtils.checkArgument(!isActive, () -> new BaseException(BasePlatformGatewayExceptionCode.TOKEN_INFO_ERROR));
    }
}
