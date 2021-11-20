package com.shadowlayover.example.mapper;

import com.shadowlayover.example.model.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单信息 Mapper 接口
 * </p>
 *
 * @author zhouzhanqi
 * @since 2021-11-17
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
