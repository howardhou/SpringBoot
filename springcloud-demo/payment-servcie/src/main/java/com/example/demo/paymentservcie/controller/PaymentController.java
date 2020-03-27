package com.example.demo.paymentservcie.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demo.common.dto.Balance;
import com.example.demo.common.service.BalanceService;
import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    // 在项目的属性文件中配置
    @Value("${sleep:0}")
    private int sleep;

    // 在共享配置文件中配置
    @Value("${share.message:none}")
    private String message;
    @Value("${share.ticket:0}")
    private int ticket;

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
        logger.info("request: /pay/balance?id=" + id + ", sleep: " + sleep);
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

        ApplicationContext sx;
        return new Balance(0, 0, ticket, message);
    }

    public Balance handleBlock(Integer id, BlockException e) {
        return new Balance(0, 0, 0, "限流");
    }
}
