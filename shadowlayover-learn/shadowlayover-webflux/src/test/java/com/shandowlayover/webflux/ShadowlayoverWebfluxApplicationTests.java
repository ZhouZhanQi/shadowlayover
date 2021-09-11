package com.shandowlayover.webflux;

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
        List<TestData> testDataList = Lists.newArrayList(new TestData(1L, "aaa", "bbb"),
                new TestData(2L, "aaa1111", "ccc"),
                new TestData(1L, "aaa", "ccc"),
                new TestData(3L, "bbb", "cccaaaa"),
                new TestData(3L, "bbb", "123455"),
                new TestData(3L, "bbb", "123455"),
                new TestData(1L, "aaa", "123cccc455"),
                new TestData(4L, "ccc", "ccc1111"),
                new TestData(5L, "ddd1", "ddd"));
        Map<String, List<TestData>> stringListMap = testDataList.stream().collect(Collectors.groupingBy(entity -> {
            return entity.getId() + "," + entity.getCode();
        }));
    
        stringListMap.entrySet().forEach(entity -> {
            entity.getValue().stream().forEach(entityVal -> {
                System.out.println("key: "+ entity.getKey() + ", value:" + entityVal.getValue());
            });
        });
        
    
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
