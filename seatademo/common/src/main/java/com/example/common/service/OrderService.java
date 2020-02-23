package com.example.common.service;

import com.example.common.dto.OrderDTO;
import com.example.common.response.ObjectResponse;

public interface OrderService {

    /**
     * 创建订单
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
