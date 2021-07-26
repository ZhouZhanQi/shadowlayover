package com.shadowlayover.adminuser.provider.service.impl;

import com.shadowlayover.adminuser.api.model.domain.SysTenant;
import com.shadowlayover.adminuser.provider.mapper.SysTenantMapper;
import com.shadowlayover.adminuser.provider.service.ISysTenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统租户 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenant> implements ISysTenantService {

}
