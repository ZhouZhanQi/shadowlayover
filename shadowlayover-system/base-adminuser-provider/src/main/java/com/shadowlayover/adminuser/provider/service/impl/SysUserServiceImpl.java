package com.shadowlayover.adminuser.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shadowlayover.adminuser.api.model.domain.SysUser;
import com.shadowlayover.adminuser.provider.mapper.SysUserMapper;
import com.shadowlayover.adminuser.provider.service.ISysDeptService;
import com.shadowlayover.adminuser.provider.service.ISysPostService;
import com.shadowlayover.adminuser.provider.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.common.security.user.ShadowlayoverUser;
import com.shadowlayover.common.security.user.ShadowlayoverUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    
    private final ISysPostService sysPostService;
    
    @Autowired
    public SysUserServiceImpl(ISysDeptService sysDeptService, ISysPostService sysPostService) {
        this.sysDeptService = sysDeptService;
        this.sysPostService = sysPostService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUserName, username));
        AssertUtils.checkNotNull(sysUser, new UsernameNotFoundException(""));
    
        //查询部门Id
        //
        
    
        ShadowlayoverUser shadowlayoverUser = new ShadowlayoverUser(sysUser.getId(), sysUser.getTenantId());
        return shadowlayoverUser;
    }
    
    @Override
    public UserDetailsService loadUserByPhone(String mobilePhone) throws UsernameNotFoundException {
        return null;
    }
    
    @Override
    public UserDetailsService loadUserBySocial(String openId) throws UsernameNotFoundException {
        return null;
    }
}
