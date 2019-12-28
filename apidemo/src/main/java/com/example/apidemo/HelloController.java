package com.example.apidemo;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/")
public class HelloController {


    @GetMapping(value = "helloworld")
    public String helloworld(){
        System.out.println("call helloworld");
        return "Hello World!";
    }

    @GetMapping(value = "welcome/{name}")
    public String welcome(@PathVariable String name){
        System.out.println("call welcome");
        return "Welcome " + name;
    }
}
