package com.example.demo.user.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.user.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

// 解决自动刷新问题
@RefreshScope
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger("Service");

    @Value("${sleep:0}")
    private int sleep;

    @Override
    @SentinelResource(value = "say-hello", blockHandler = "handleBlock")
    public String sayHello(String name) {
        logger.info("call sayHello, sleep: " + sleep );

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello, " + name ;
    }

    // 添加 handleBlock 方法
    public String handleBlock(String name, BlockException e) {
        return name + " - 限流";
    }
}
