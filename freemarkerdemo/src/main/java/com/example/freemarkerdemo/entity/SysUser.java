package com.example.freemarkerdemo.entity;

public class SysUser {
    private long id;
    private String name;
    private String phone;


    public SysUser() {
    }
    public SysUser(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "SysUser [id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }


}