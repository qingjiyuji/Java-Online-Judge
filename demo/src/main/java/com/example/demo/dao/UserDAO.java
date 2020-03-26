package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDAO {
    @Insert("INSERT INTO user (userId,userName,userPassword,userEmail) values(#{userId},#{userName},#{userPassword},#{userEmail})")
    void insert(String userId,String userName,String userPassword,String userEmail);

    @Select("select * from user where userId = #{userId}")
    User queryUserById(@Param("userId") String userId);





}
