package com.shadowlayover.common.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/18-15:42
 * @desc: 用户服务
 * </pre>
 */
public interface ShadowlayoverUserDetailService extends UserDetailsService {

    /**
     * 根据手机号码登录
     * @param mobilePhone
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByPhone(String mobilePhone) throws UsernameNotFoundException;

    /**
     * 根据社交账号登录
     * @return
     * @param openId
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserBySocial(String openId) throws UsernameNotFoundException;
}
