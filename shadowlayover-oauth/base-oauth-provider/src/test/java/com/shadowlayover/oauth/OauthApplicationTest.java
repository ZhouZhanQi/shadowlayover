package com.shadowlayover.oauth;

import com.shadowlayover.common.db.generator.CodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class OauthApplicationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void testPasswordEncode() {
        System.out.println(passwordEncoder.encode("123456"));
    }

    public static void main(String[] args) {
        CodeGenerator.generatorCode("zhouzhanqi", "jdbc:mysql://192.168.1.98:13306/shadowlayover_oauth", "root", "123456");
    }
}
