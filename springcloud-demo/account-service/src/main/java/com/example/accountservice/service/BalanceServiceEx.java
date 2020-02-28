package com.example.accountservice.service;


import com.example.demo.common.dto.Balance;
import com.example.demo.common.service.BalanceService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", fallback = BalanceServiceFallback.class)
public interface BalanceServiceEx extends BalanceService {

}