package com.example.readinglist.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;

// Reader实现了UserDetails接口以及其中的方法，这样Reader就能代表Spring Security里的用户了
// @Entity注解，表示这是一个JPA实体
@Entity
@Table(name = "tb_reader")
public class Reader implements UserDetails {

    private static final long serialVersionUID = 1L;

    // @Id注解，表明这是实体的ID
    @Id
    private Integer id;
    private String username;
    private String fullname;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("READER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 账号不过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 不加锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //证书不过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 不禁用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
