package com.shadowlayover.example.service.impl;

import com.shadowlayover.example.model.domain.Order;
import com.shadowlayover.example.mapper.OrderMapper;
import com.shadowlayover.example.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-11-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
