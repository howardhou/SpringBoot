package com.example.shirodemo.entity;


import java.io.Serializable;

public class User implements Serializable {
    boolean locked;
    String username;
    String password;
    String credentialsSalt;

    public User(){

    }

    public User(String username, String password, String credentialsSalt, Boolean locked){
        this.username = username;
        this.password = password;
        this.credentialsSalt = credentialsSalt;
        this.locked = locked;
    }

    public Boolean getLocked() {
        return locked;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCredentialsSalt() {
        return credentialsSalt;
    }
}
