package com.shadowlayover.adminuser;

import com.shadowlayover.common.db.generator.CodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseComponentApplicationTests {

    @Test
    public void codeGenerate() {
        CodeGenerator.generatorCode("zhouzhanqi", "jdbc:mysql://192.168.1.98:13306/shadowlayover_component", "root", "123456");
    }
}
