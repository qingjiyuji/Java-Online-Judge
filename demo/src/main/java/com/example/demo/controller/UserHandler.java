package com.example.demo.controller;


import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public void set(@RequestBody User user) {
        redisTemplate.opsForValue().set("user", user);
    }

    @GetMapping("/get/{key}")
    public User get(@PathVariable("key") String key) {
        return (User) redisTemplate.opsForValue().get(key);
    }

    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key) {
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);
    }
}
