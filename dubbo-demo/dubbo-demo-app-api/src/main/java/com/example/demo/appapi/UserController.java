package com.example.demo.appapi;

import com.example.demo.user.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {

        return userService.sayHello(name);
    }
}
