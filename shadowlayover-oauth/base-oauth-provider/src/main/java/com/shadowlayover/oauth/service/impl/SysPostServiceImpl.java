package com.shadowlayover.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.utils.AssertUtils;
import com.shadowlayover.oauth.model.domain.SysPost;
import com.shadowlayover.oauth.mapper.SysPostMapper;
import com.shadowlayover.oauth.model.domain.SysUserPost;
import com.shadowlayover.oauth.model.enums.OauthResponseCode;
import com.shadowlayover.oauth.service.ISysPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadowlayover.oauth.service.ISysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统租户职位 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {

    private final ISysUserPostService sysUserPostService;

    @Autowired
    public SysPostServiceImpl(ISysUserPostService sysUserPostService) {
        this.sysUserPostService = sysUserPostService;
    }

    @Override
    public SysPost getByUserId(Long userId) {
        SysUserPost userPost = sysUserPostService.getOne(Wrappers.lambdaQuery(SysUserPost.class).eq(SysUserPost::getUserId, userId));
        AssertUtils.checkNotNull(userPost, new BusinessException(OauthResponseCode.POST_NOT_FOUND_ERROR));
        return this.getBaseMapper().selectOneIgnoreTenant(Wrappers.lambdaQuery(SysPost.class).eq(SysPost::getId, userPost.getPostId()));
    }
}
