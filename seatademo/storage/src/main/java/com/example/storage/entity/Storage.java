package com.example.storage.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * t_storage
 * @author 
 */
@Data
public class Storage implements Serializable {
    private Integer id;

    private String commodityCode;

    private String name;

    private Integer count;

    private static final long serialVersionUID = 1L;
}