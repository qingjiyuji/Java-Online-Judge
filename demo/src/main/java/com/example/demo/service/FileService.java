package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

@Service
public class FileService {

    public String main() throws Exception {
        String RUN_DIR = "/Users/wutengda/Documents/GitHub/demo/test";

        String cmd1 = "cp Main.java test" + "cp MainTest.java test";
        String cmd2 = "javac -cp /Users/wutengda/Documents/GitHub/demo/junit-4.13-beta-2.jar:/Users/wutengda/Documents/GitHub/demo/hamcrest-all-1.3.jar Main.java  MainTest.java";
        String cmd3 = "java -cp .:/Users/wutengda/Documents/GitHub/demo/junit-4.13-beta-2.jar:/Users/wutengda/Documents/GitHub/demo/hamcrest-all-1.3.jar org.junit.runner.JUnitCore MainTest";

        Runtime.getRuntime().exec("rm -rf " + RUN_DIR).waitFor();
        Runtime.getRuntime().exec("mkdir " + RUN_DIR).waitFor();
        Runtime.getRuntime().exec(cmd1).waitFor();

        String result = runBash(cmd2, RUN_DIR) + runBash(cmd3, RUN_DIR);

        return result;

    }

    //method of creat a  temp Script
    public File createTempScript(String cmd) throws IOException {
        File tempScript = File.createTempFile("tempScript.sh", null);
        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);
        printWriter.println("#!/bin/bash");
        printWriter.println(cmd);
        printWriter.close();
        return tempScript;
    }

    public String runBash(String cmd, String dir) throws Exception {
        File tempScript = createTempScript(cmd); // 创建临时⽂文件
        // 新建PB对象
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", tempScript.toString());
        pb.directory(new File(dir)); // 定义PB运⾏行行⽬目录
        Process process = pb.start();
        String result = "";

        if (process.waitFor(5, TimeUnit.SECONDS)) { // 进程执⾏行行
            /*此处省去了了如何获取进程的输⼊入输出以及错误输出信息代码，*/
            result = cmdResultInfo(process.getInputStream());
            process.getOutputStream().close();
        } else { // 程序超时
            process.destroyForcibly(); // kill掉该进程的⼦子进程
            killUnixProcess(process); // kill盖进程
        }
        return result;
    }

    private String cmdResultInfo(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        BufferedReader cmdExecuteInfoReader = null;
        try {
            cmdExecuteInfoReader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String cmdExecuteInfoLine;
            while ((cmdExecuteInfoLine = cmdExecuteInfoReader.readLine()) != null) {
                builder.append(cmdExecuteInfoLine);
                builder.append("\n");
            }
            return builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (cmdExecuteInfoReader != null) {
                    cmdExecuteInfoReader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //get id of previous process
    public int getUnixPID(Process process) throws Exception {
        System.out.println(process.getClass().getName());
        if (process.getClass().getName().equals("java.lang.UNIXProcess")) {
            Class cl = process.getClass();
            Field field = cl.getDeclaredField("pid");
            field.setAccessible(true);
            Object pidObject = field.get(process);
            return (Integer) pidObject;
        } else {
            throw new IllegalArgumentException("Needs to be a UNIXProcess");
        }
    }

    public int killUnixProcess(Process process) throws Exception {
        int pid = getUnixPID(process);
        return Runtime.getRuntime().exec("pkill -TERM -P " + pid).waitFor();
    }

}
