package com.example.order;

import com.example.common.dto.OrderDTO;
import com.example.common.response.ObjectResponse;
import com.example.common.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class OrderApplicationTests {

    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setUserId("1");
        orderDTO.setOrderAmount(BigDecimal.valueOf(10));
        orderDTO.setOrderCount(1);
        ObjectResponse<OrderDTO> response = orderService.createOrder(orderDTO);
    }

}
