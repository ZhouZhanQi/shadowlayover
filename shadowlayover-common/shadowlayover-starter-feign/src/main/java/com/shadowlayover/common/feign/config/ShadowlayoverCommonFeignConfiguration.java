package com.shadowlayover.common.feign.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.http.ContentType;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.common.core.model.code.CommonExceptionCode;
import com.shadowlayover.common.web.utils.ResponseUtils;
import feign.FeignException;
import feign.Logger;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/29-11:28
 * @desc:
 * </pre>
 */
public class ShadowlayoverCommonFeignConfiguration implements InitializingBean {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        //分离异常类型
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                //区分业务异常已服务异常
                return FeignException.errorStatus(methodKey, response);
            }
        };
    }

    /**
     * 自定义sentinel异常处理
     * @return
     */
    @Bean
    public BlockExceptionHandler shadowlayoverBlockHandler() {
        return (httpServletRequest, httpServletResponse, e) -> {
            ResponseData responseData = ResponseData.fail(CommonExceptionCode.SERVICE_ERROR);
            if (e instanceof FlowException) {
                responseData = ResponseData.fail(CommonExceptionCode.SERVICE_FLOW_LIMIT);
            } else if (e instanceof DegradeException) {
                responseData = ResponseData.fail(CommonExceptionCode.SERVICE_DOWN_GRADE);
            } else if (e instanceof AuthorityException) {
                responseData = ResponseData.fail(CommonExceptionCode.SERVICE_AUTH_RULE_LIMIT);
            } else if (e instanceof ParamFlowException) {
                responseData = ResponseData.fail(CommonExceptionCode.SERVICE_HOT_PARAM_FLOW_LIMIT);
            } else if (e instanceof SystemBlockException) {
                responseData = ResponseData.fail(CommonExceptionCode.SERVICE_SYS_RULE_LIMIT);
            }
            ResponseUtils.responseWriter(httpServletResponse, ContentType.JSON.toString(), HttpStatus.OK.value(), responseData);
        };
    }

    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(OBJECT_MAPPER);
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    @Bean
    public Encoder feignEncoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(OBJECT_MAPPER);
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new SpringEncoder(objectFactory);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 日期序列化为long
        OBJECT_MAPPER.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 反序列化时, 忽略不认识的字段, 而不是抛出异常
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 日期序列化支持LocalDateTime LocalDate
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        timeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        timeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        timeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        timeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        timeModule.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        OBJECT_MAPPER.registerModule(timeModule);
    }
}