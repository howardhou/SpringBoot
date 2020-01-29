package com.example.springsecuritydemo.db;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

    public User getUserByUsername(String userName){
        return  new User(1, "howard", "123456");
    }
}
