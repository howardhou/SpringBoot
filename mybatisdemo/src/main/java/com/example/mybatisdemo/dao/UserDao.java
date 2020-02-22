package com.example.mybatisdemo.dao;

import com.example.mybatisdemo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //@Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(int id);
    User getUserWithAssetsById(int id);
    void save(User user);
}
