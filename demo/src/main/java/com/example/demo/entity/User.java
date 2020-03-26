package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;

    public User(){}

}
