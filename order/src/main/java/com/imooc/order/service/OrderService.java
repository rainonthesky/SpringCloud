package com.imooc.order.service;

import com.imooc.order.dto.OrderDto;

public interface OrderService {
    // 创建订单
    OrderDto create(OrderDto orderDto);
}
