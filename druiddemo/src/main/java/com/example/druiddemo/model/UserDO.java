package com.example.druiddemo.model;

public class UserDO {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;

    public UserDO(){
        super();
    }

    public UserDO(String name, String sex, Integer age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName() + this.getSex() + this.getAge();
    }
}
