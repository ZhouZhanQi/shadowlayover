package com.shadowlayover.oauth;

import com.google.common.base.Joiner;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class OauthApplicationTest {


    @Test
    public void testAAA() {

        List<String> alphaList = Lists.newArrayList("a", "b", "c", "d", "e", "f");
        List<String> stringList = alphaList.parallelStream().map(alpha -> {
            if (alpha.equals("a") || alpha.equals("e")) {
                return null;
            }

            return alpha;
        }).filter(str -> StringUtils.isNotBlank(str)).collect(Collectors.toList());


        System.out.println(Joiner.on(",").join(stringList));
    }
}
