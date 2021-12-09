package com.shadowlayover.example.message;

import com.shadowlayover.common.core.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.support.RocketMQListenerContainer;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/24-14:35
 * @desc: 消息监听
 * </pre>
 */
@Slf4j
@Component
public class ExampleMessageListener {

    @Bean
    public Consumer<Message<String>> messageconsumer() {
        return message -> {
            log.info("接收消息: {}", message);
            throw new BaseException(123);

//            return;
        };
    }
}
