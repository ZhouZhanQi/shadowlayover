package com.shadowlayover.oauth.service.impl;

import com.shadowlayover.oauth.model.bo.SysUserBo;
import com.shadowlayover.oauth.model.domain.SysUser;
import com.shadowlayover.oauth.mapper.SysUserMapper;
import com.shadowlayover.oauth.service.ISysDeptService;
import com.shadowlayover.oauth.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final ISysDeptService sysDeptService;


    @Autowired
    public SysUserServiceImpl(ISysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    @Override
    public SysUserBo loadUserDetailByUsername(String username) {
        return null;
    }

    @Override
    public SysUserBo loadUserDetailByMobilePhone(String mobilePhone) {
        return null;
    }
}
