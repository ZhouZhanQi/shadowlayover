package com.shadowlayover.component;

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

}
