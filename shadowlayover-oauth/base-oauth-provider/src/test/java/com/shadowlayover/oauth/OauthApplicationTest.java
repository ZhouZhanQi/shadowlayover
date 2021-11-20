package com.shadowlayover.oauth;

import com.shadowlayover.common.core.utils.JacksonUtils;
import com.shadowlayover.oauth.model.bo.SysUserBo;
import com.shadowlayover.oauth.service.ISysUserService;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class OauthApplicationTest {



    @Data
    static class TestTime {
        private LocalDateTime signTime;
    }
    
}
