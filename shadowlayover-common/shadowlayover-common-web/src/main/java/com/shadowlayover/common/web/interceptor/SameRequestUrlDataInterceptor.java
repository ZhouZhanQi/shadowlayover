package com.shadowlayover.common.web.interceptor;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.google.common.collect.Maps;
import com.shadowlayover.common.core.utils.JacksonUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/31-14:20
 * @desc: 重复请求地址与数据拦截器
 * </pre>
 */
@Component
public class SameRequestUrlDataInterceptor extends RepeatSubmitInterceptor {
    
    public final String REPEAT_PARAMS = "repeatParams";
    
    public final String REPEAT_TIME = "repeatTime";
    
    public final String SESSION_REPEAT_KEY = "repeatData";
    
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request) throws Exception {
        String requestJsonParam = JacksonUtils.pojo2Json(request.getParameterMap());
        Map<String, String> paramMap = Maps.newHashMapWithExpectedSize(2);
        //请求参数
        paramMap.put(REPEAT_PARAMS, requestJsonParam);
        //请求时间
        paramMap.put(REPEAT_TIME, Long.toString(LocalDateTimeUtil.toEpochMilli(LocalDateTime.now(ZoneId.systemDefault()))));
        //缓存中获取token
        String requestUri = request.getRequestURI();
    
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        return false;
    }
    
}
