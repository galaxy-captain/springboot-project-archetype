package me.galaxy.archetype.infra.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 6:20 下午
 **/
public class FileUtils {

    public static String readJsonFile(String path) {
        try {
            File file = ResourceUtils.getFile(path);
            FileInputStream in = new FileInputStream(file);
            byte[] bytes = in.readAllBytes();
            String content = new String(bytes, "UTF-8");
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }

}