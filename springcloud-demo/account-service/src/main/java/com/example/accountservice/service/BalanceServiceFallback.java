package com.example.accountservice.service;

import com.example.demo.common.dto.Balance;
import com.example.demo.common.service.BalanceService;

public class BalanceServiceFallback implements BalanceService {

    @Override
    public Balance getBalance(Integer id) {
        return null;
    }
}
