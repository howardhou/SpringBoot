package com.example.springsecuritydemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloController {
    @RequestMapping(value = "hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/my/message")
    public String message(){
        return "you have a message!";
    }

    @RequestMapping("/hi")
    public String hi(String name){
        return "hi " + name;
    }
}
