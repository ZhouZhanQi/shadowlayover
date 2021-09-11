package com.shadowlayover.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.shadowlayover.common.security.user.ShadowlayoverUser;
import com.shadowlayover.oauth.model.domain.SysDept;
import com.shadowlayover.oauth.model.domain.SysUser;
import com.shadowlayover.oauth.mapper.SysUserMapper;
import com.shadowlayover.oauth.model.enums.OauthResponseCode;
import com.shadowlayover.oauth.service.ISysDeptService;
import com.shadowlayover.oauth.service.ISysPostService;
import com.shadowlayover.oauth.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.common.security.user.ShadowlayoverUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService, ShadowlayoverUserDetailService {

    private final ISysDeptService sysDeptService;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SysUserServiceImpl(ISysDeptService sysDeptService, PasswordEncoder passwordEncoder) {
        this.sysDeptService = sysDeptService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUserName, username));
        AssertUtils.checkNotNull(sysUser, new UsernameNotFoundException(OauthResponseCode.USERNAME_NOT_FOUND_ERROR.getMessage()));

        //查询部门
        SysDept sysDept = sysDeptService.getByUserId(sysUser.getId());

        //todo 用户状态校验
        ShadowlayoverUser shadowlayoverUser = new ShadowlayoverUser(sysUser.getId(), sysUser.getTenantId(), sysDept.getId(), sysUser.getUserName(), sysUser.getPassword(), true, false, false, false, null);
        return shadowlayoverUser;
    }

    @Override
    public UserDetails loadUserByPhone(String mobilePhone) throws UsernameNotFoundException {
        SysUser sysUser = this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getMobilePhone, mobilePhone));
        AssertUtils.checkNotNull(sysUser, new UsernameNotFoundException(OauthResponseCode.USERNAME_NOT_FOUND_ERROR.getMessage()));

        //查询部门
        SysDept sysDept = sysDeptService.getByUserId(sysUser.getId());

        //todo 用户状态校验
        ShadowlayoverUser shadowlayoverUser = new ShadowlayoverUser(sysUser.getId(), sysUser.getTenantId(), sysDept.getId(), sysUser.getUserName(), sysUser.getPassword(), true, false, false, false, null);
        return shadowlayoverUser;
    }

    @Override
    public UserDetails loadUserBySocial(String openId) throws UsernameNotFoundException {
        return null;
    }

}
