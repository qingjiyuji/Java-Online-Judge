package com.example.demo.controller;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class registerController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/register")
    public String insert(
            @RequestParam(value = "userid", required = false) String userid,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pass", required = false) String pass,
            @RequestParam(value = "email", required = false) String email
    ){

        userDAO.insert(userid,name,pass,email);

        return "login";
    }

}
