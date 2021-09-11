package com.shadowlayover.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shadowlayover.oauth.model.domain.SysDept;
import com.shadowlayover.oauth.model.domain.SysPost;
import com.shadowlayover.oauth.model.domain.SysUserPost;
import com.shadowlayover.oauth.mapper.SysDeptMapper;
import com.shadowlayover.oauth.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadowlayover.oauth.service.ISysPostService;
import com.shadowlayover.oauth.service.ISysUserPostService;
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

    @Autowired
    public SysDeptServiceImpl(ISysPostService sysPostService) {
        this.sysPostService = sysPostService;
    }

    @Override
    public SysDept getByUserId(Long userId) {
        //查询用户职位
        SysPost sysPost = sysPostService.getByUserId(userId);
        //查询用户部门
        return this.getById(sysPost.getDeptId());
    }
}
