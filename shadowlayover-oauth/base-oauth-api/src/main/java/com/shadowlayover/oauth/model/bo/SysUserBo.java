package com.shadowlayover.oauth.model.bo;

import com.shadowlayover.common.core.model.BaseBo;
import com.shadowlayover.oauth.model.domain.*;
import lombok.*;

import java.util.List;

/**
 * @author zhouzhanqi
 * @date 2021/9/12 2:57 上午
 * @desc 用户信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserBo extends SysUser {

    /**
     * 部门信息
     */
    private SysDept sysDept;

    /**
     * 职位信息
     */
    private SysPost sysPost;

    /**
     * 租户信息
     */
    private SysTenant sysTenant;

    /**
     * 角色信息
     */
    private List<SysRole> sysRoleList;
}
