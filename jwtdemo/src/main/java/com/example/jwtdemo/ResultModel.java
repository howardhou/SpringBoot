package com.example.jwtdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultModel<T> implements java.io.Serializable {
    private int status;

    private String message;

    private T data;
}
