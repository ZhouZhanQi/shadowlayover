package com.shadowlayover.example.api;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.example.model.domain.Order;
import com.shadowlayover.example.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/17-13:45
 * @desc:
 * </pre>
 */
@RequiredArgsConstructor
@RestController
public class OrderApi {

    private final IOrderService orderService;

    private final StreamBridge streamBridge;

    @PostMapping("order")
    public ResponseData<Order> createOrder() {
        streamBridge.send("messageproducer-out-0", "重试消息");
        return ResponseData.success();
    }
}
