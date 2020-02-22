package com.example.druiddemo.controller;

import com.example.druiddemo.model.UserDO;
import com.example.druiddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    // MyBatis
    @GetMapping("/getUser/{id}")
    public UserDO getUserById(@PathVariable int id) {
        //System.out.println("test :id: "+id);
        return userService.getUserById(id);
    }
}
