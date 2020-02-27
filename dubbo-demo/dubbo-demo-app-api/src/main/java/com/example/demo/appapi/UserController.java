package com.example.demo.appapi;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demo.user.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference
    private UserService userService;

    // 使用 @NacosValue 获取属性值
    @NacosValue(value = "${company:none}", autoRefreshed = true)
    private String company;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {

        return userService.sayHello(company + ": " + name);
    }
}
