package com.example.mybatisdemo.jdbc;

import com.example.mybatisdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// 得在 RestController里面测试， 在 Test里面没有测试通过
@Repository
public class JdbcTemplateDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findUserList() {
        List<User> list = jdbcTemplate.query("select * from tb_user", new Object[]{}, new BeanPropertyRowMapper(User.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
