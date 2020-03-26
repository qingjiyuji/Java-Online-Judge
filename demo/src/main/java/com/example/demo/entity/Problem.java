package com.example.demo.entity;

import lombok.Data;

@Data
public class Problem {
    private String problemId;

    private String problemTitle;

    private String problemContent;

    private String problemAnswer;

    public Problem(){
    }

}
