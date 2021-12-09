package com.shadowlayover.example;

import com.google.common.collect.Maps;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/17-13:36
 * @desc:
 * </pre>
 */
@SpringBootTest
public class ExampleOrderApplicationTest {


    @Autowired
    private StreamBridge streamBridge;

    @Test
    public void testRocketMq() {
//        MessageChannel messageChannel = new DirectChannel();
//        Map<String, Object> paramMap = Maps.newHashMap();
//        paramMap.put(RocketMQHeaders.TAGS, "base-message");
//        paramMap.put(RocketMQHeaders.KEYS, "test");
//        MessageHeaders messageHeaders = new MessageHeaders(paramMap);
//        Message<String> message = MessageBuilder.createMessage("123", messageHeaders);
//        messageChannel.send(message);
        streamBridge.send("message", "123");

    }
}
