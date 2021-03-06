package com.shadowlayover.common.feign.config;

import com.shadowlayover.common.core.model.constants.CoreConstants;
import com.shadowlayover.common.core.utils.TraceUtils;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-10:36
 * @desc: feign自动配置
 * </pre>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@Import(ShadowlayoverCommonFeignConfiguration.class)
public class ShadowlayoverFeignAutoConfiguration {
    
    @Bean("shadowlayoverFeignRequestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return template -> {
            //传递上下文信息
            //链路信息传递
            String traceId = MDC.get(CoreConstants.LOG_TRACE_ID);
            if (StringUtils.isNotBlank(traceId)) {
                template.header(CoreConstants.SHADOWLOYOVER_TRACE_ID, traceId);
                return;
            }

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (Objects.isNull(attributes) || Objects.isNull(attributes.getRequest().getHeaderNames())) {
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headName = headerNames.nextElement();
                String headValue = request.getHeader(headName);
                if (headName.equalsIgnoreCase(CoreConstants.SHADOWLOYOVER_TRACE_ID)) {
                    TraceUtils.mdcTraceId(headValue);
                    template.header(headName, headValue);
                }

                if (headName.equalsIgnoreCase(CoreConstants.SHADOWLOYOVER_USER_INFO)) {
                    template.header(headName, headValue);
                }
            }
        };
    }
}
