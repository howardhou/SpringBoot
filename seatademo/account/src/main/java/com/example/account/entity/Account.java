package com.example.account.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_account
 * @author 
 */
@Data
public class Account implements Serializable {
    private Integer id;

    private String userId;

    private Double amount;

    private static final long serialVersionUID = 1L;
}