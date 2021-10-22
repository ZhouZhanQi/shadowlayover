package com.shadowlayover.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.oauth.model.bo.SysUserBo;
import com.shadowlayover.oauth.model.convert.SysUserConvert;
import com.shadowlayover.oauth.model.domain.SysDept;
import com.shadowlayover.oauth.model.domain.SysPost;
import com.shadowlayover.oauth.model.domain.SysTenant;
import com.shadowlayover.oauth.model.domain.SysUser;
import com.shadowlayover.oauth.mapper.SysUserMapper;
import com.shadowlayover.oauth.model.enums.OauthResponseCode;
import com.shadowlayover.oauth.service.ISysDeptService;
import com.shadowlayover.oauth.service.ISysPostService;
import com.shadowlayover.oauth.service.ISysTenantService;
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

    private final ISysPostService sysPostService;
    
    private final ISysTenantService sysTenantService;
    
    private final SysUserConvert sysUserConvert;

    @Autowired
    public SysUserServiceImpl(ISysDeptService sysDeptService, ISysPostService sysPostService, ISysTenantService sysTenantService, SysUserConvert sysUserConvert) {
        this.sysDeptService = sysDeptService;
        this.sysPostService = sysPostService;
        this.sysTenantService = sysTenantService;
        this.sysUserConvert = sysUserConvert;
    }

    @Override
    public SysUserBo loadUserDetailByUsername(String username) {
        SysUser sysUser = this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUserName, username));
        return completionSysUserData(sysUser);
    }

    @Override
    public SysUserBo loadUserDetailByMobilePhone(String mobilePhone) {
        SysUser sysUser = this.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getMobilePhone, mobilePhone));
        return completionSysUserData(sysUser);
    }
    
    /**
     * 补全用户信息
     * @param sysUser
     * @return
     */
    private SysUserBo completionSysUserData(SysUser sysUser) {
        SysUserBo userBo = sysUserConvert.convert2Bo(sysUser);
        AssertUtils.checkNotNull(sysUser, new BusinessException(OauthResponseCode.USERNAME_OR_PASSWORD_ERROR));
        //查询租户
        SysTenant sysTenant = sysTenantService.getById(sysUser.getTenantId());
        AssertUtils.checkNotNull(sysTenant, new BusinessException(OauthResponseCode.TENANT_NOT_FOUND_ERROR));
        //查询角色
    
        //部门信息
        SysDept sysDept = sysDeptService.getByUserId(sysUser.getId());
        AssertUtils.checkNotNull(sysDept, new BusinessException(OauthResponseCode.DEPT_NOT_FOUND_ERROR));
    
        //职位信息
        SysPost sysPost = sysPostService.getByUserId(sysUser.getId());
        AssertUtils.checkNotNull(sysPost, new BusinessException(OauthResponseCode.POST_NOT_FOUND_ERROR));
    
        //复制更新用户信息
        sysUserConvert.updateBo(userBo, SysUserBo.builder()
                .sysTenant(sysTenant)
                .sysDept(sysDept)
                .sysPost(sysPost)
                .build());
        return userBo;
    }
}
