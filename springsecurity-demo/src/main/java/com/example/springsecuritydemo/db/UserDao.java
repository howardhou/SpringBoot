package com.example.springsecuritydemo.db;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    public User getUserByUsername(String userName){
        String pwd = new BCryptPasswordEncoder().encode("1234");
        return  new User(1, "user", pwd);
    }
}
