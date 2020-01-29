package com.example.jwtdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements java.io.Serializable{
    private Long userId;
    private String username;
    private String password;

    private String token;
}
