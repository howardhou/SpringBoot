package com.example.springsecuritydemo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetailService implements UserDetailsService {

   @Autowired
   UserDao userDao;

   @Autowired
   RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userDao.getUserByUsername(s);

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {

                ArrayList<String> userRoles = roleDao.listByUserId(user.getId());
                ArrayList<GrantedAuthority> list = new ArrayList<>();

                for (String roleName : userRoles) {
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
                    list.add(simpleGrantedAuthority);
                }

                return list;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };

        return userDetails;
    }
}
