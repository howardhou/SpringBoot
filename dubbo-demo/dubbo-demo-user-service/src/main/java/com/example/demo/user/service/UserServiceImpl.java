package com.example.demo.user.service;

import com.example.demo.user.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger("Service");

    @Override
    public String sayHello(String name) {
        logger.info("call sayHello");
        return "Hello, " + name + " (from Dubbo with Spring Boot)";
    }
}
