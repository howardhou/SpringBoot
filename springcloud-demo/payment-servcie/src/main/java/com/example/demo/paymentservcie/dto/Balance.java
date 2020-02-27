package com.example.demo.paymentservcie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    private int id;
    private int diamond;
    private int ticket;
    private String message;

    public Balance(int id, int diamond, int ticket){
        this(id, diamond, ticket, "OK");
    }

}
