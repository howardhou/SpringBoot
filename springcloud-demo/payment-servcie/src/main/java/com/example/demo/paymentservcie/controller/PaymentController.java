package com.example.demo.paymentservcie.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.common.dto.Balance;
import com.example.demo.common.service.BalanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RefreshScope
public class PaymentController implements BalanceService {
    @Value("${sleep:0}")
    private int sleep;

    final static Map<Integer, Balance> balanceMap = new HashMap() {{
        put(1, new Balance(1, 10, 1000));
        put(2, new Balance(2, 0, 10000));
        put(3, new Balance(3, 100, 0));
    }
    };

    @Override
    @RequestMapping(value = "/pay/balance", method = RequestMethod.GET)
    @SentinelResource(value = "protected-resource", blockHandler = "handleBlock")
    public Balance getBalance(@RequestParam("id") Integer id) {
        System.out.println("request: /pay/balance?id=" + id + ", sleep: " + sleep);
        if (sleep > 0) {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (id != null && balanceMap.containsKey(id)) {
            return balanceMap.get(id);
        }

        return new Balance(0, 0, 0,"none");
    }

    public Balance handleBlock(Integer id, BlockException e) {
        return new Balance(0, 0, 0, "限流");
    }
}
