package com.example.accountservice.service;


import com.example.demo.common.service.BalanceService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment-service", fallback = BalanceServiceFallback.class)
public interface BalanceServiceEx extends BalanceService {

}