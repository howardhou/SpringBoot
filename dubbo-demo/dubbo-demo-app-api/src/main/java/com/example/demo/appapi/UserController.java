package com.example.demo.appapi;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.demo.user.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class UserController {

    @Reference(mock = "true" )
    private UserService userService;

    // 使用 @NacosValue 获取属性值
//    @NacosValue(value = "${company:none}", autoRefreshed = true)
    @Value("${company:none}")
    private String company;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable String name) {

        return userService.sayHello(name + " at " + company);
    }
}
