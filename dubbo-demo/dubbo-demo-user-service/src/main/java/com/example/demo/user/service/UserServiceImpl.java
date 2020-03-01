package com.example.demo.user.service;

import com.example.demo.user.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger("Service");

    @Value("${sleep:0}")
    private int sleep;

    @Override
    public String sayHello(String name) {
        logger.info("call sayHello, sleep: " + sleep);

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello, " + name + " (from Dubbo with Spring Boot)";
    }
}
