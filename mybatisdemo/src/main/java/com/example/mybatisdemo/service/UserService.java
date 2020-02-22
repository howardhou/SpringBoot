package com.example.mybatisdemo.service;

import com.example.mybatisdemo.jdbc.JdbcTemplateDao;
import com.example.mybatisdemo.jpa.UserJpa;
import com.example.mybatisdemo.jpa.UserRepository;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.dao.UserDao;
import com.example.mybatisdemo.jpa.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // 2. 测试 JDBCTemplate
    private final JdbcTemplateDao jdbcTemplateDao;
    // 3. 测试 MyBatis
    private final UserDao userDao;

    private final UserJpa userJpa;
    private final UserRepository userRepository;

    // 不建议使用属性注入, 建议使用构造注入的方式进行bean的注入
    // https://blog.csdn.net/baidu_30809315/article/details/78201847
    @Autowired
    public UserService(JdbcTemplateDao jdbcTemplateDao, UserDao userDao, UserJpa userJpa, UserRepository userRepository) {
        this.jdbcTemplateDao = jdbcTemplateDao;
        this.userDao = userDao;
        this.userJpa = userJpa;
        this.userRepository = userRepository;
    }

    // Mybatis
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public User getUserWithAssetsById(int id) {
        return userDao.getUserWithAssetsById(id);
    }

    // jdbc template
    public List<User> getAllUser() {
        return jdbcTemplateDao.findUserList();
    }

    //jpa
    public UserEntity getUserEntityById(int id){
        return userJpa.getUser(id);
    }

    public List<UserEntity> getAllUserEntity(){
        return userRepository.findAll();
    }
}
