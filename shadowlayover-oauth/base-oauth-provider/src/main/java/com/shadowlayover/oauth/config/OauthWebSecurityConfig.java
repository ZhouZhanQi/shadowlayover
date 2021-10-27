package com.shadowlayover.oauth.config;

import com.shadowlayover.common.security.handler.ShadowlayoverAuthenticationFailureHandler;
import com.shadowlayover.common.security.handler.ShadowlayoverAuthenticationSuccessHandler;
import com.shadowlayover.common.security.user.ShadowlayoverUserDetailService;
import com.shadowlayover.oauth.authentication.sms.SmsCodeAuthenticationSecurityConfig;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/18-16:30
 * @desc:
 * </pre>
 */
@EnableWebSecurity
public class OauthWebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    
    @Autowired
    private ShadowlayoverUserDetailService shadowlayoverUserDetailService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public AuthenticationSuccessHandler shadowlayoverSuccessHandler() {
        return new ShadowlayoverAuthenticationSuccessHandler();
    }
    
    @Bean
    public AuthenticationFailureHandler shadowlayoverFailureHandler() {
        return new ShadowlayoverAuthenticationFailureHandler();
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config = http.requestMatchers().anyRequest()
                .and()
                .formLogin()
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .authorizeRequests();

        //任何请求
        config.antMatchers("/base-oauth/token")
                .permitAll()
                .anyRequest()
                //都需要身份认证
                .authenticated()
                .and()
                //csrf跨站请求
                .csrf()
                .disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(shadowlayoverUserDetailService).passwordEncoder(passwordEncoder());
    }
}
