package com.shadowlayover.example.message;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/24-15:23
 * @desc: 消息发送
 * </pre>
 */
@Slf4j
@Component
public class ExampleMessageSender {


//    @Bean
//    public Supplier<String> messageproducer() {
//        return () -> {
//            String message = "abc";
//            log.info("发送消息: {}", message);
//            return message;
//        };
//    }
}
