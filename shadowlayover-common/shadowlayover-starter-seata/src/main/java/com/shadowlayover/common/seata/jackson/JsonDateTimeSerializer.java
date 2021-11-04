package com.shadowlayover.common.seata.jackson;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.seata.rm.datasource.undo.parser.spi.JacksonSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/9/28-18:08
 * @desc:
 * </pre>
 */
public class JsonDateTimeSerializer implements JacksonSerializer<LocalDateTime> {
    @Override
    public Class<LocalDateTime> type() {
        return LocalDateTime.class;
    }
    
    @Override
    public JsonSerializer<LocalDateTime> ser() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
    }
    
    @Override
    public JsonDeserializer<? extends LocalDateTime> deser() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS"));
    }
}
