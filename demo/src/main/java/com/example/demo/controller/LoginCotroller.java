package com.example.demo.controller;

import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginCotroller {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/")
    public String login(){
        return "login";
    }



    @PostMapping()
    public String login1(
            @RequestParam(value = "userid", required = false) String userid,
            @RequestParam(value = "password", required = false) String password
    ){

        User user = userDAO.queryUserById(userid);
        redisTemplate.opsForValue().set("user", user);

        if (password.equals(user.getUserPassword())) {
            return "redirect:/index";
        } else {
            return "redirect:/login";
        }
    }
}
