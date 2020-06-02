package me.galaxy.archetype.infra.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @Author duanxiaolei
 * @Date 2020/6/2 6:44 下午
 **/
public class MD5Utils {

    private final static char[] md5String = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encode(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        String md5 = encode(bytes);
        return md5;
    }

    public static String encode(byte[] bytes) {

        try {

            // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(bytes);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = md5String[byte0 >>> 4 & 0xf];
                str[k++] = md5String[byte0 & 0xf];
            }

            // 返回经过加密后的字符串
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getCause());
        }
    }

}