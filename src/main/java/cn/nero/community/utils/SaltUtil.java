package cn.nero.community.utils;

import java.util.Random;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/3/26
 */
public class SaltUtil {

    /**
     * 生成随机盐
     * @param len
     * @return
     */
    public static String getSalt(int len){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-_+=".toCharArray();
        int length = chars.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(chars[new Random().nextInt(length)]);
        }
        return sb.toString();
    }

    /**
     * 重载方法,默认6位
     * @return
     */
    public static String getSalt(){
        return getSalt(6);
    }

}
