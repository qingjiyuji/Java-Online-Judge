package com.example.demo.dao;


import com.example.demo.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper
public interface ProblemDAO {

    @Select("select * from problem where problemId = #{problemId}")
    Problem problemInfo(@Param("problemId") String problemId);

    @Select("select * from problem")
    List<Problem> problemList();
}
