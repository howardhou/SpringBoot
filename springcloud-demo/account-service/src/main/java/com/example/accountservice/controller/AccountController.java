package com.example.accountservice.controller;

import com.example.accountservice.dto.User;
import com.example.accountservice.service.BalanceServiceEx;
import com.example.demo.common.dto.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/acc")
public class AccountController {
    final static Map<Integer, User> userMap = new HashMap() {{
        put(1, new User(1, "张三"));
        put(2, new User(2, "李四"));
        put(3, new User(3, "王五"));
    }
    };

    @Autowired
    BalanceServiceEx balanceServiceEx;

    @RequestMapping("/user")
    public User getUser(@RequestParam Integer id) {
        if(id != null && userMap.containsKey(id)) {
            User user = userMap.get(id);

 Balance balance = balanceServiceEx.getBalance(user.getId());
user.setBalance(balance);

            return user;
        }
        return new User(0, "");
    }

}
