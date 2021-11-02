package com.shadowlayover.gateway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/2-16:41
 * @desc: web security
 * </pre>
 */
@EnableWebSecurity
public class ShadowlayoverSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略静态资源
//        web.ignoring().antMatchers();
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/base-oauth/oauth/token")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Client();
    }
}
