package com.shadowlayover.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/12-14:30
 * @desc:
 * </pre>
 */
public class AuthorizationServerConfig {

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    }
}
