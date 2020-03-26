package com.example.demo.controller;

import com.example.demo.dao.ProblemDAO;
import com.example.demo.entity.Problem;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class problemController {
    @Autowired
    private ProblemDAO problemDAO;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/problem")
    public String problem(@RequestParam String id,
            HttpSession session
    ) {

        Problem problemInfo = problemDAO.problemInfo(id);
        String problemId = problemInfo.getProblemId();
        String problemTitle = problemInfo.getProblemTitle();
        String problemContent = problemInfo.getProblemContent();
        String problemAnswer = problemInfo.getProblemAnswer();

        //读取每道题的信息
        session.setAttribute("problemId", problemId);
        session.setAttribute("problemTitle", problemTitle);
        session.setAttribute("problemContent", problemContent);
        session.setAttribute("problemAnswer", problemAnswer);

        try {
            // 新建文件

            String fileName = "MainTest.java";
            String filePath = "/Users/wutengda/Documents/GitHub/demo/";
            File file = new File(filePath + fileName);
            PrintWriter output = new PrintWriter(file);
            output.print(problemAnswer);
            output.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        //清除上一次判题结果缓存
        session.removeAttribute("result");

        return "problem";
    }


    /**
     * 用户上传文件
     * @param file
     * @param session
     * @throws Exception
     */
    @PostMapping(value = "problem")
    public void uploadFile(@RequestParam("testUpload") MultipartFile file,
                       HttpSession session
    ) throws Exception {
        System.out.println(file);
        session.removeAttribute("reslut");

        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);

        // 文件上传后的路径
        String filePath = "/Users/wutengda/Documents/GitHub/demo/";
        File dest = new File(filePath + fileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = fileService.main();
        System.out.println(result);
        session.setAttribute("result", result);
    }

    /**
     * 用户上传输入答案
     * @param file
     * @param userInput
     * @param session
     * @throws Exception
     */
    @PostMapping(value = "problem/userInput")
    public String uploadInput(@RequestParam(value = "userInput") String userInput,
                       HttpSession session
    ) throws Exception {
        try {
            // 新建文件

            String fileName = "Main.java";
            String filePath = "/Users/wutengda/Documents/GitHub/demo/";
            File file = new File(filePath + fileName);
            PrintWriter output = new PrintWriter(file);
            output.print(userInput);
            output.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("用户答案上传完成！");

        String result = fileService.main();

        System.out.println("用户判题结果：");
        System.out.println(result);

        session.setAttribute("result",result);
        return "problem";
    }
}

