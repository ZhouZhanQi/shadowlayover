package com.shadowlayover.oauth.service.impl;

import com.shadowlayover.common.security.user.ShadowlayoverUser;
import com.shadowlayover.oauth.model.bo.SysUserBo;
import com.shadowlayover.oauth.service.IShadowlayoverUserService;
import com.shadowlayover.oauth.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Service;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 2:53 上午
 * @desc 系统用户认证服务
 */
@Slf4j
@Service
public class ShadowlayoverUserServiceImpl implements IShadowlayoverUserService {

    private final ISysUserService sysUserService;



    @Autowired
    public ShadowlayoverUserServiceImpl(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Override
    public UserDetails loadUserByPhone(String mobilePhone) throws UsernameNotFoundException {
        SysUserBo sysUserBo = sysUserService.loadUserDetailByUsername(mobilePhone);
        //todo 用户状态校验
        ShadowlayoverUser shadowlayoverUser = new ShadowlayoverUser(sysUserBo.getId(), sysUserBo.getTenantId(), sysUserBo.getSysDept().getId(), sysUserBo.getUserName(), sysUserBo.getPassword(), true, false, false, false, null);
        return shadowlayoverUser;
    }

    @Override
    public UserDetails loadUserBySocial(String openId) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
