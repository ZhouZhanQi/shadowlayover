package com.shadowlayover.gateway.clients;


import com.shadowlayover.gateway.model.constants.PlatformGatewayConstants;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/30-15:12
 * @desc: 远程调用
 * </pre>
 */
@FeignClient(value = PlatformGatewayConstants.BASE_OAUTH_SERVICE, path = "/base-oauth", fallbackFactory = BaseOauthTokenRemoteClient.BaseOauthTokenRemoteFallback.class)
public interface BaseOauthTokenRemoteClient {

    @GetMapping(value = "/oauth/check_token")
    Map<String, Object> checkToken(@SpringQueryMap Map<String, String> param);

    @Component
    class BaseOauthTokenRemoteFallback implements FallbackFactory<BaseOauthTokenRemoteClient> {
        @Override
        public BaseOauthTokenRemoteClient create(Throwable cause) {
            return null;
        }
    }
}
