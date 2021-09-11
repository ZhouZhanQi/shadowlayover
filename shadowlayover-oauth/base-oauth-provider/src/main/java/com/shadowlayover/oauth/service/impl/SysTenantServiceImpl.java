package com.shadowlayover.oauth.service.impl;

import com.shadowlayover.oauth.model.domain.SysTenant;
import com.shadowlayover.oauth.mapper.SysTenantMapper;
import com.shadowlayover.oauth.service.ISysTenantService;
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
