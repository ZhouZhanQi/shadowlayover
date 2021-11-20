package com.shadowlayover.message.client;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.message.model.constants.MessageConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/17-14:03
 * @desc: 临时测试使用
 * </pre>
 */
@FeignClient(value = "example-order", path = "/example-order", fallbackFactory = ExampleOrderClient.ExampleOrderRemoteFallback.class)
public interface ExampleOrderClient {

    @PostMapping("order")
    Object testOrder();

    @Component
    public class ExampleOrderRemoteFallback implements ExampleOrderClient {
        @Override
        public Object testOrder() {
            return null;
        }
    }
}
