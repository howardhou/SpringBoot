package com.example.druiddemo.service;

import com.example.druiddemo.dao.UserDao;
import com.example.druiddemo.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    public UserDO getUserById(int id) {
        return userDao.getUserById(id);
    }
}
