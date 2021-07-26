package com.shadowlayover.adminuser.provider.service.impl;

import com.shadowlayover.adminuser.api.model.domain.SysUser;
import com.shadowlayover.adminuser.provider.mapper.SysUserMapper;
import com.shadowlayover.adminuser.provider.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
