package com.example.springsecuritydemo;

import com.example.springsecuritydemo.jwt.ResultDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public String index(){
        return "hi user";
    }

    // 路由映射到/users
    @RequestMapping(value = "/users", produces="application/json; charset=UTF-8")
    public ResultDTO usersList() {

        ArrayList<String> users =  new ArrayList<String>(){{
            add("freewolf");
            add("tom");
            add("jerry");
        }};

        return new ResultDTO(0, "", users);
    }
}
