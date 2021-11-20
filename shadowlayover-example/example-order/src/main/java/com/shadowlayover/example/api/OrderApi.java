package com.shadowlayover.example.api;

import com.shadowlayover.common.core.model.ResponseData;
import com.shadowlayover.example.model.domain.Order;
import com.shadowlayover.example.service.IOrderService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("order")
    public ResponseData<Order> createOrder() throws InterruptedException {
        Thread.sleep(20000);
        return ResponseData.success();
    }
}
