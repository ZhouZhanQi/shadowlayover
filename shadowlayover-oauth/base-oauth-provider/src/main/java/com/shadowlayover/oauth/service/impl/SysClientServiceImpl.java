package com.shadowlayover.oauth.service.impl;

import com.shadowlayover.oauth.model.domain.SysClient;
import com.shadowlayover.oauth.mapper.SysClientMapper;
import com.shadowlayover.oauth.service.ISysClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-10-27
 */
@Service
public class SysClientServiceImpl extends ServiceImpl<SysClientMapper, SysClient> implements ISysClientService {

}
