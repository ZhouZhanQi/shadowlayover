package com.shadowlayover.oauth;

import lombok.Data;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class OauthApplicationTest {



    @Data
    static class TestTime {
        private LocalDateTime signTime;
    }
    
}
