package com.example.druiddemo.service;

import com.example.druiddemo.dao.UserDao;
import com.example.druiddemo.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {
    public UserDO getUserById(int id);
}
