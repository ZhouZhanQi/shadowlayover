package com.shadowlayover.oauth.controller;

import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.model.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/18-14:21
 * @desc: oauth2控制器
 * </pre>
 */
@RestController()
@RequiredArgsConstructor
public class Oauth2Controller {
    
    private final TokenEndpoint tokenEndpoint;

    private final CheckTokenEndpoint checkTokenEndpoint;
//
//    @SneakyThrows
//    @GetMapping("/tokens")
//    public ResponseData<OAuth2AccessToken> oauth2AccessTokenGet(Principal principal, @RequestParam Map<String, String> parameterMap) {
//        return ResponseData.success(tokenEndpoint.getAccessToken(principal, parameterMap).getBody());
//    }

    @SneakyThrows
    @PostMapping("/tokens")
    public ResponseData<OAuth2AccessToken> oauth2AccessTokenPost(Principal principal, @RequestBody Map<String, String> parameterMap) {
        return ResponseData.success(tokenEndpoint.postAccessToken(principal, parameterMap).getBody());
    }

    @GetMapping("/tokens/{token}")
    public ResponseData<Map<String, ?>> oauth2AccessTokenCheck(@PathVariable("token") String token) {
        return ResponseData.success(checkTokenEndpoint.checkToken(token));
    }
}
