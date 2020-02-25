package com.example.order.service;

import com.example.common.dto.AccountDTO;
import com.example.common.dto.OrderDTO;
import com.example.common.enums.RspStatusEnum;
import com.example.common.response.ObjectResponse;
import com.example.common.service.AccountService;
import com.example.common.service.OrderService;
import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Service
public class OrderServiceImp implements OrderService {

    @Reference(version = "1.0.0")
    AccountService accountService;

    @Autowired
    OrderMapper orderMapper;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Override
    public ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO) {

        System.out.println("Order: 全局事务，XID = " + RootContext.getXID());

        ObjectResponse<OrderDTO> response = new ObjectResponse<>();
        //扣减用户账户
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(orderDTO.getUserId());
        accountDTO.setAmount(orderDTO.getOrderAmount());
        ObjectResponse objectResponse = accountService.decreaseAccount(accountDTO);

        logger.info("成功扣金额");

        //生成订单号
        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-",""));

        //生成订单
        Order tOrder = new Order();
        BeanUtils.copyProperties(orderDTO,tOrder);
        tOrder.setCount(orderDTO.getOrderCount());
        tOrder.setAmount(orderDTO.getOrderAmount().doubleValue());
        try {
            orderMapper.createOrder(tOrder);

            //int a = 10/0;
        } catch (Exception e) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        if (objectResponse.getStatus() != 200) {
            response.setStatus(RspStatusEnum.FAIL.getCode());
            response.setMessage(RspStatusEnum.FAIL.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.SUCCESS.getCode());
        response.setMessage(RspStatusEnum.SUCCESS.getMessage());
        return response;
    }
}
