package com.example.druiddemo.dao;

import com.example.druiddemo.model.TbCard;
import org.springframework.stereotype.Repository;

/**
 * TbCardDAO继承基类
 */
@Repository
public interface TbCardDAO extends MyBatisBaseDao<TbCard, Integer> {
}