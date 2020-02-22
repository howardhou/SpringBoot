package com.example.druiddemo.dao;

import com.example.druiddemo.model.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //@Select("SELECT * FROM user WHERE id = #{id}")
    UserDO getUserById(int id);
}
