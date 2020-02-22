package com.example.mybatisdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// MyBatis 的 持久化类(PO) ： MyBatis 直接采用了 POJO 作为持久化类，和 JavaBean 没有任何区别, 它是低侵入式的
// 持久化类和数据库中的表之间是通过XML文件建立映射关系的
// 忽略Bean中不存在的字段 @JsonIgnoreProperties(ignoreUnknown = true)
// 解决异常： (through reference chain: com.example.mybatisdemo.entity.User_$$_jvst1b4_0["handler"]
// 忽略 handler 字段 : https://blog.csdn.net/qq_33548914/article/details/79991280
@JsonIgnoreProperties({"handler"})
public class User {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private Card card;
    private List<Asset> assetList;

    public User(){
        super();
    }

    public User(String name, String sex, Integer age){
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    @Override
    public String toString() {
        return this.getName() + this.getSex() + this.getAge();
    }
}
