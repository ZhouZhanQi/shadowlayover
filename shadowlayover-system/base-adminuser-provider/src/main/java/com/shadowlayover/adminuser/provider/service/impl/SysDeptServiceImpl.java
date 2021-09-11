package com.shadowlayover.adminuser.provider.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shadowlayover.adminuser.api.model.domain.SysDept;
import com.shadowlayover.adminuser.api.model.domain.SysPost;
import com.shadowlayover.adminuser.api.model.domain.SysUserPost;
import com.shadowlayover.adminuser.provider.mapper.SysDeptMapper;
import com.shadowlayover.adminuser.provider.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadowlayover.adminuser.provider.service.ISysPostService;
import com.shadowlayover.adminuser.provider.service.ISysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 系统租户部门 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {
    
    private final ISysPostService sysPostService;
    
    private final ISysUserPostService sysUserPostService;
    
    @Autowired
    public SysDeptServiceImpl(ISysPostService sysPostService, ISysUserPostService sysUserPostService) {
        this.sysPostService = sysPostService;
        this.sysUserPostService = sysUserPostService;
    }
    
    @Override
    public SysDept getByUserId(Long userId) {
        SysUserPost userPost = sysUserPostService.getOne(Wrappers.lambdaQuery(SysUserPost.class).eq(SysUserPost::getUserId, userId));
    
        SysPost sysPost = sysPostService.getById(postId);
        return Objects.isNull(sysPost) ? null : this.getById(sysPost.getDeptId());
    }
}
