package com.shadowlayover.common.security.user;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/8/16-13:39
 * @desc: 扩展用户信息
 * </pre>
 */
@Getter
public class ShadowlayoverUser extends User {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 部门Id
     */
    private Long deptId;

    /**
     * 租户Id
     */
    private Long tenantId;

    public ShadowlayoverUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     *
     * @param userId 用户Id
     * @param deptId 部门Id
     * @param tenantId 租户Id
     * @param username 用户名
     * @param password 密码
     * @param enabled 是否启用
     * @param accountNonExpired 账户是否过期
     * @param credentialsNonExpired 认证是否过期
     * @param accountNonLocked 是否锁定
     * @param authorities 权限集合
     */
    public ShadowlayoverUser(Long userId, Long tenantId, Long deptId, String username, String password,
                             boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                             boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.deptId = deptId;
        this.tenantId = tenantId;
    }
}
