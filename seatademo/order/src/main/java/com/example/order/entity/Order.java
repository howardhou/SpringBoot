package com.example.order.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_order
 * @author 
 */
@Data
public class Order implements Serializable {
    private Integer id;

    private String orderNo;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Double amount;

    private static final long serialVersionUID = 1L;
}