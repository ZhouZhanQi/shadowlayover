package com.shadowlayover.oauth.config;

import com.shadowlayover.common.cache.RedisCacheHelper;
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
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));

        DefaultTokenServices defaultTokenServices = createDefaultTokenServices();
        addUserDetailsService(defaultTokenServices);
        endpoints.tokenGranter(tokenGranter(endpoints, defaultTokenServices))
                .tokenServices(defaultTokenServices);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证请求
        security.allowFormAuthenticationForClients()
                //spel表达式 访问公钥端点（/auth/token_key）需要认证
                .tokenKeyAccess("permitAll()")
                //spel表达式 访问令牌解析端点（/auth/check_token）需要认证
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clientDetailsServices.setSelectClientDetailsSql(BaseOauthConstant.SELECT_CLIENT_DETAIL_SQL);
        clientDetailsServices.setFindClientDetailsSql(BaseOauthConstant.FIND_CLIENT_DETAIL_SQL);
        clients.withClientDetails(clientDetailsServices);
    }

    /**
     * 重点
     * 先获取已经有的五种授权，然后添加我们自己的进去
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer
     * @return TokenGranter
     */
    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints, DefaultTokenServices tokenServices) {
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        // 短信验证码模式
        granters.add(new SmsCodeTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), GrantTypeEnum.SMS_CODE.getKey(), redisCacheHelper));
        // // 验证码模式
        // granters.add(new CaptchaTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
        //         endpoints.getOAuth2RequestFactory(), redisService));
        // // 社交登录模式
        // granters.add(new SocialTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(),
        //         endpoints.getOAuth2RequestFactory(), redisService, factory));
        // 增加密码模式
        granters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }

    /**
     * 创建默认的tokenServices
     *
     * @return DefaultTokenServices
     */
    private DefaultTokenServices createDefaultTokenServices() {
        DefaultTokenServices tokenServices = new SingleLoginTokenServices(isSingleLogin);
        tokenServices.setTokenStore(redisTokenStore());
        // 支持刷新Token
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
}
