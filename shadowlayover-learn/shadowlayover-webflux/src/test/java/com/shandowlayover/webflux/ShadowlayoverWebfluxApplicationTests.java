package com.shandowlayover.webflux;

import cn.hutool.http.HttpUtil;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shadowlayover.common.core.exceptions.BusinessException;
import com.shadowlayover.common.core.utils.JacksonUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ShadowlayoverWebfluxApplicationTests {
    
    @Test
    public void testJsoup() throws IOException {
        Map<String, Integer> entityAttrIndex = Maps.newHashMap();
        Document doc = Jsoup.connect("http://youjia.chemcp.com/").get();
        Elements trRecords = doc.getElementsByClass("cpbaojia")
                .select("table")
                .first()
                .select("tbody")
                .select("tr");
    
        Elements headElements = trRecords.stream()
                .findFirst()
                .orElseThrow(() -> new BusinessException())
                .getElementsByTag("td");
        for (int i = 0; i < headElements.size(); i++) {
            entityAttrIndex.put(headElements.get(i).text(), i);
        }

        
        List<Map<String, String>> objList = Lists.newArrayList();
        for (int i = 0; i < trRecords.size(); i++) {
            if (i == 0) {
                continue;
            }
            Elements rowList = trRecords.get(i).getElementsByTag("td");
            Map<String, String> objMap = Maps.newHashMap();
            entityAttrIndex.entrySet().forEach(entity -> {
                objMap.put(entity.getKey(), rowList.get(entity.getValue()).text());
            });
            objList.add(objMap);
        }
        System.out.println(JacksonUtils.pojo2Json(objList));
    }
    
    @Test
    public void testJvHeOliApi() {
        //聚合油价API
        String oliJson = HttpUtil.get("http://apis.juhe.cn/gnyj/query?key=e88f9a12836fbbca3c10138228de273f");
        System.out.println(oliJson);
    }
    
    @Test
    public void testBaiduYiyuanApi() {
        String path = "http://todayoilprice.api.bdymkt.com/today";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.GET, path);
        request.setCredentials("0282234609744175b52b1cd27cb34d46", "4fafc2e8e8b4494a84df4e64476b1607");
        
        // request.addHeaderParameter("X-Bce-Signature", "AppCode/2790118a19a847d6b952212402823b14");
        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");
        request.addQueryParameter("prov", "广西");
        ApiExplorerClient client = new ApiExplorerClient();
        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            System.out.println(response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
