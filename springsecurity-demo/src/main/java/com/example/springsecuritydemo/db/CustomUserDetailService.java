package com.example.springsecuritydemo.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailService implements UserDetailsService {

   @Autowired
   UserDao userDao;

   @Autowired
   RoleDao roleDao;

   private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userDao.getUserByUsername(s);

        ArrayList<String> userRoles = roleDao.listByUserId(user.getId());

        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();

        for (String roleName : userRoles) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            list.add(simpleGrantedAuthority);
        }

        logger.warn("***** role : " + list);

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), list);

    }
}
