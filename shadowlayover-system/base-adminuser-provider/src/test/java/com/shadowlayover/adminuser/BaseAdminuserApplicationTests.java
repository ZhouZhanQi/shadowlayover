package com.shadowlayover.adminuser;

import com.shadowlayover.oauth.model.domain.SysUser;
import com.shadowlayover.oauth.service.ISysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseAdminuserApplicationTests {


    @Autowired
    private ISysUserService sysUserService;

    /**
     *
     */
    @Test
    public void testShadow() {
        final SysUser sysUser = sysUserService.getById(1);
    }
}
