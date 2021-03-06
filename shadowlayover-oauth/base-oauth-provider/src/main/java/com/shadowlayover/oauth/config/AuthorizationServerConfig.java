package com.shadowlayover.oauth.config;

import com.shadowlayover.common.cache.RedisCacheHelper;
import com.shadowlayover.common.cache.model.code.RedisKeyPrefix;
import com.shadowlayover.common.core.utils.JacksonUtils;
import com.shadowlayover.common.security.convert.ShadowlayoverUserConvert;
import com.shadowlayover.common.security.user.ShadowlayoverUser;
import com.shadowlayover.common.security.user.ShadowlayoverUserDetailService;
import com.shadowlayover.oauth.granter.SmsCodeTokenGranter;
import com.shadowlayover.oauth.model.constants.BaseOauthConstant;
import com.shadowlayover.oauth.model.enums.GrantTypeEnum;
import com.shadowlayover.oauth.service.oauth.ClientDetailsServices;
import com.shadowlayover.oauth.service.oauth.SingleLoginTokenServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/12-14:30
 * @desc:
 * </pre>
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final ClientDetailsServices clientDetailsServices;

    private final RedisConnectionFactory redisConnectionFactory;

    private final ShadowlayoverUserDetailService shadowlayoverUserService;

    private final AuthenticationManager authenticationManager;
    
    private final RedisCacheHelper redisCacheHelper;

    private boolean isSingleLogin = false;

    @Bean
    public RedisTokenStore redisTokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("oauth:");
        return redisTokenStore;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(shadowlayoverUserService);
        endpoints.tokenStore(redisTokenStore());

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));

        DefaultTokenServices defaultTokenServices = createDefaultTokenServices();
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        addUserDetailsService(defaultTokenServices);
        endpoints.tokenGranter(tokenGranter(endpoints, defaultTokenServices))
                .tokenServices(defaultTokenServices);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // ????????????????????????
        security.allowFormAuthenticationForClients()
                //spel????????? ?????????????????????/auth/token_key???????????????
                .tokenKeyAccess("permitAll()")
                //spel????????? ???????????????????????????/auth/check_token???????????????
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clientDetailsServices.setSelectClientDetailsSql(BaseOauthConstant.SELECT_CLIENT_DETAIL_SQL);
        clientDetailsServices.setFindClientDetailsSql(BaseOauthConstant.FIND_CLIENT_DETAIL_SQL);
        clients.withClientDetails(clientDetailsServices);
    }

    /**
     * ??????
     * ?????????????????????????????????????????????????????????????????????
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer
     * @return TokenGranter
     */
    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints, DefaultTokenServices tokenServices) {
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        // ?????????????????????
        granters.add(new SmsCodeTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), GrantTypeEnum.SMS_CODE.getKey(), redisCacheHelper));
        // // ???????????????
        // granters.add(new CaptchaTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
        //         endpoints.getOAuth2RequestFactory(), redisService));
        // // ??????????????????
        // granters.add(new SocialTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
        //         endpoints.getOAuth2RequestFactory(), redisService, factory));
        // ??????????????????
        granters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }

    /**
     * ???????????????tokenServices
     *
     * @return DefaultTokenServices
     */
    private DefaultTokenServices createDefaultTokenServices() {
        DefaultTokenServices tokenServices = new SingleLoginTokenServices(isSingleLogin);
        tokenServices.setTokenStore(redisTokenStore());
        // ????????????Token
        tokenServices.setSupportRefreshToken(Boolean.TRUE);
        tokenServices.setReuseRefreshToken(Boolean.FALSE);
        tokenServices.setClientDetailsService(clientDetailsServices);
        addUserDetailsService(tokenServices);
        return tokenServices;
    }


    private void addUserDetailsService(DefaultTokenServices tokenServices) {
        if (shadowlayoverUserService != null) {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(shadowlayoverUserService));
            tokenServices.setAuthenticationManager(new ProviderManager(Collections.singletonList(provider)));
        }
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (oAuth2AccessToken, oAuth2Authentication) -> {
            // ?????????????????????map
            final Map<String, Object> additionMessage = new HashMap<>(2);

            // ???????????????????????????
            ShadowlayoverUser user = (ShadowlayoverUser) oAuth2Authentication.getUserAuthentication().getPrincipal();
            //?????????60s
            redisCacheHelper.set(RedisKeyPrefix.OAUTH_TOKEN_USER, oAuth2AccessToken.getValue(), ShadowlayoverUserConvert.INSTANCE.convertToTokenUser(user), oAuth2AccessToken.getExpiresIn() + 60, TimeUnit.SECONDS);
            // ??????????????????????????????????????????token
            if (oAuth2Authentication.getUserAuthentication() == null) {
                return oAuth2AccessToken;
            }

            // ?????????????????????
            if (user != null) {
                additionMessage.put(BaseOauthConstant.SHADOWLAYOVER_USER_ID, user.getUserId());
                additionMessage.put(BaseOauthConstant.SHADOWLAYOVER_USER_NAME, user.getUsername());
                additionMessage.put(BaseOauthConstant.SHADOWLAYOVER_USER_TYPE, user.getUserId());
                additionMessage.put(BaseOauthConstant.SHADOWLAYOVER_TENANT_ID, user.getTenantId());
            }

            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionMessage);
            return oAuth2AccessToken;
        };
    }
}
