package com.example.mybatisdemo.dao;

import com.example.mybatisdemo.entity.Card;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDao {
    Card getCardById(int id);
}
