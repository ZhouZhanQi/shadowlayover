package com.shadowlayover.oauth;

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

}
