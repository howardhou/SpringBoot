package com.example.demo.user;

public class UserServiceMock implements UserService {
    public UserServiceMock(){

    }

    @Override
    public String sayHello(String name) {
        System.out.print("------ 降级");
        return "降级";
    }
}
