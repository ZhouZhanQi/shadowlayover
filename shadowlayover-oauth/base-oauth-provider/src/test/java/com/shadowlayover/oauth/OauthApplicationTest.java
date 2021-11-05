package com.shadowlayover.oauth;

import com.shadowlayover.common.core.utils.JacksonUtils;
import com.shadowlayover.oauth.model.bo.SysUserBo;
import com.shadowlayover.oauth.service.ISysUserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OauthApplicationTest {

    @Autowired
    private ISysUserService sysUserService;


    @Test
    public void testPasswordEncode() {
        SysUserBo sysUserBo = sysUserService.loadUserDetailByMobilePhone("13800138000");
        System.out.println(JacksonUtils.pojo2Json(sysUserBo));
    }

    public static void main(String[] args) {
//        CodeGenerator.generatorCode("zhouzhanqi", "jdbc:mysql://192.168.1.98:13306/shadowlayover_oauth", "root", "123456");

//        String patch = "^[\u4e00-\u9fa5]{1}[A-HJ-NP-Z]{1}[A-Z0-9]{5,6}[A-HJ-NP-Z0-9 挂学警港澳]{1}$";


//        System.out.println(Pattern.matches(patch, "和ADP820挂"));

        List<Boolean> booleanList = Lists.newArrayList(true, false);

        System.out.println(booleanList.stream().allMatch(locked -> locked));

    }
}
