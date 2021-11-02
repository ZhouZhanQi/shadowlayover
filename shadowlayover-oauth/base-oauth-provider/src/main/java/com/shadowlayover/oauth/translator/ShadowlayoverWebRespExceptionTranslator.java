package com.shadowlayover.oauth.translator;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.oauth.model.code.OauthResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/14-14:04
 * @desc: WEB响应异常处理类
 * </pre>
 */
@Slf4j
@Component
public class ShadowlayoverWebRespExceptionTranslator implements WebResponseExceptionTranslator {
    
    @Override
    public ResponseEntity<ResponseData<?>> translate(Exception e) throws Exception {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        log.error("authentication error", e);
        if (e instanceof UnsupportedGrantTypeException) {
            return status.body(ResponseData.fail(OauthResponseCode.UNSUPPORT_GRANT_TYPE_ERROR));
        }
        
        if (e instanceof InvalidTokenException) {
            return status.body(ResponseData.fail(OauthResponseCode.INVALID_TOKEN_ERROR));
        }
        
        if (e instanceof InvalidScopeException) {
            return status.body(ResponseData.fail(OauthResponseCode.INVALID_SCOPE_ERROR));
        }
        
        if (e instanceof InvalidGrantException) {
            return status.body(ResponseData.fail(OauthResponseCode.INVALID_GRANT_ERROR));
        }
        return status.body(ResponseData.fail(OauthResponseCode.AUTHENTICATION_ERROR));
    }
}
