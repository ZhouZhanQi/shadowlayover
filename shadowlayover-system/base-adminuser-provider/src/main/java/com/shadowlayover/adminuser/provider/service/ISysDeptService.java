package com.shadowlayover.adminuser.provider.service;

import com.shadowlayover.adminuser.api.model.domain.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统租户部门 服务类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-07-27
 */
public interface ISysDeptService extends IService<SysDept> {
    
    /**
     * 根据职位Id查询部门信息
     * @userId 用户Id
     * @return 返回部门信息
     */
    SysDept getByUserId(Long userId);
    
}
