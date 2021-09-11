package com.shadowlayover.oauth.service.impl;

import com.shadowlayover.oauth.model.domain.SysUserPost;
import com.shadowlayover.oauth.mapper.SysUserPostMapper;
import com.shadowlayover.oauth.service.ISysUserPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户职位关联 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {

}
