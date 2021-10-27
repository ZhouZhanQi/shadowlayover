package com.shadowlayover.oauth;

import com.shadowlayover.common.db.generator.CodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@SpringBootTest
public class OauthApplicationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void testPasswordEncode() {
        System.out.println(passwordEncoder.encode("123456"));
    }

    public static void main(String[] args) {
//        CodeGenerator.generatorCode("zhouzhanqi", "jdbc:mysql://192.168.1.98:13306/shadowlayover_oauth", "root", "123456");

//        String patch = "^[\u4e00-\u9fa5]{1}[A-HJ-NP-Z]{1}[A-Z0-9]{5,6}[A-HJ-NP-Z0-9 挂学警港澳]{1}$";


//        System.out.println(Pattern.matches(patch, "和ADP820挂"));

    }
}
