package com.example.accountservice.dto;

import com.example.demo.common.dto.Balance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;

    private Balance balance;

    public User(int id , String name){
        this(id,name,null);
    }
}
