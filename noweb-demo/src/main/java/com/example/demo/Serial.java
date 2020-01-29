package com.example.demo;

import java.io.Serializable;

public class Serial implements Serializable {

    private static final long serialVersionUID = -1762472608094320838L;
    private int id;
    private String name;
    private int age;

    public Serial(int id, String name) {
        this.id = id;
        this.name = name;
        this.age = 18;
    }

    public String toString() {
        return "DATA: " + id + " " +name + " " + age;
    }
}
