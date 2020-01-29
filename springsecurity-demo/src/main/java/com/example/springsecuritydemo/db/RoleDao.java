package com.example.springsecuritydemo.db;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoleDao {

    public ArrayList<String> listByUserId(Integer userId){
         ArrayList<String> list = new ArrayList<>();
         list.add("ADMIN");
         list.add("USER");
         return list;
    }
}
