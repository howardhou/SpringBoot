package com.example.springsecuritydemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/index")
    public String index(){
        return "hi admin";
    }

    @RequestMapping("/getAdmin")
    public String getAdmin(){
        return "I am admin";
    }
}
