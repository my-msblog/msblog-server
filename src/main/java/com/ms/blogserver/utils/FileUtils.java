package com.ms.blogserver.utils;

import com.ms.blogserver.core.exception.ProgramException;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.model.bo.FileSimpleBO;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 文件工具类
 * @author: zhh
 * @time: 2021/6/9
 */
@Slf4j
public class FileUtils {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String NAME = "FILE_NAME";
    public static final String TIME = "FILE_NAME";

    /**
     * 文件
     * 获取当前目录下所有.log文件的文件名和修改时间
     *
     * @param directory 文件目录
     * @return {@link List}<{@link FileSimpleBO}>
     * @throws Exception 异常
     */
    public static List<FileSimpleBO> files(File directory) throws Exception {
        File[] listFiles = directory.listFiles();
        List<FileSimpleBO> list = new ArrayList<>();
        assert listFiles != null;
        for (File f : listFiles) {
            if (f.isDirectory()) {
                //存在最终的节点
                files(f);
            } else {
                if (f.getName().endsWith("log")) {
                    FileSimpleBO fileSimpleBO = new FileSimpleBO();
                    fileSimpleBO.setFileName(f.getName());
                    fileSimpleBO.setTime(new Date(f.lastModified()));
                    list.add(fileSimpleBO);
                }
            }
        }
        return list;
    }

    /**
     * 文件时间格式转换
     *
     * @param time 文件修改时间
     * @return {@link String}
     */
    public static String getTime(long time) {
        synchronized (FORMAT) {
            Date d = new Date(time);
            return FORMAT.format(d);
        }
    }

    /**
     * 加载日志
     *
     * @param url url
     * @return {@link String}
     */
    public static String loadLog(String url){
        try{
            File file = new File(url+".log");
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String strLine;
            StringBuilder stringBuffer = new StringBuilder();
            while ((strLine = br.readLine()) != null)   {
                stringBuffer.append(strLine);
                stringBuffer.append("\n");
            }
            fstream.close();
            return stringBuffer.toString();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new ProgramException(ErrorContexts.FILE_ERR);
        }
    }

    /**
     * 写文件
     *
     * @param filepath filepath
     * @param content  内容
     */
    public static void fileWriterMethod(String filepath, String content) {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.append(content);
        } catch (Exception e){
            throw new ProgramException(ErrorContexts.FILE_ERR);
        }
    }
}
