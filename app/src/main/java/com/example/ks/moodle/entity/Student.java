package com.example.ks.moodle.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String password;
    private String name;
    private String description;

    public Student(String id, String password, String name, String description) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
