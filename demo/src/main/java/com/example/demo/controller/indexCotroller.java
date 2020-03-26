package com.example.demo.controller;

import com.example.demo.dao.ProblemDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class indexCotroller {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProblemDAO problemDAO;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/index")
    public String index(
            Model model,
            HttpSession session
    ){

        User loginUser = (User)redisTemplate.opsForValue().get("user");

        User user = userDAO.queryUserById(loginUser.getUserId());

        String userName = user.getUserName();
        session.setAttribute("userName",userName);

        List<Problem> problemsList = problemDAO.problemList();

        model.addAttribute("problemsList",problemsList);

        return "index";
    }
}
