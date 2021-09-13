package com.shandowlayover.webflux;

import cn.hutool.extra.emoji.EmojiUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.utils.JacksonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class ShadowlayoverWebfluxApplicationTests {
    
    /**
     *
     */
    @Test
    public void testShadow() {
        //
        String str = "\uD83C\uDF3Aꦿ᭄华丽的冒险꧔ꦿএ᭄\uD83D\uDC95";
        // String str = "看🐱‍👓🐱‍🐉💋👏🙄🤗😍😎☺😚👨‍🦱👨‍🦰👨‍🦱🎐🎐🎗🛒🍞🍞🥙🥙🛴🚛🛺💥💥☪[]~(￣▽￣)~*( •̀ ω •́ )✧㎏₨₩₥";
    
        byte[] encode = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8)));
    
        System.out.println(new String(Base64.getDecoder().decode(encode)));
    }
    
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class TestData {
        
        private Long id;
        
        private String code;
        
        private String value;
    }
}
