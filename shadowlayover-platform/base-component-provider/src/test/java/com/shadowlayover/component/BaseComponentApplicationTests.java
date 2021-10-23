package com.shadowlayover.component;

import cn.hutool.core.lang.generator.UUIDGenerator;
import com.shadowlayover.common.db.generator.CodeGenerator;
import com.shadowlayover.component.service.ISysConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseComponentApplicationTests {

    @Autowired
    private ISysConfigService sysConfigService;
    
    @Test
    public void testSeata() {
        sysConfigService.testSeata();
    }
    
    @Test
    public void testAAA() {
        System.out.println(new UUIDGenerator().next().replace("-", ""));
    }
}
