package cn.nero.community.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/3/26
 */
public class Md5Util {

    /**
     * 获取md5加密算法得到的密码
     * @param password password
     * @param salt salt
     * @param hash 散列次数
     * @return
     */
    public static String getMd5(String password, String salt, Integer hash){
        return new Md5Hash(password, salt, hash).toHex();
    }

    /**
     * 重载方法,默认散列1024次
     * @param password
     * @param salt
     * @return
     */
    public static String getMd5(String password, String salt){
        return getMd5(password, salt, 1024);
    }

    /**
     * 重载方法.不使用盐
     * @param password
     * @return
     */
    public static String getMd5(String password){
        return getMd5(password, "");
    }

    /**
     * a9e752f560a0041e08933b5401f0ffca
     * 64c8b1e43d8ba3115ab40bcea57f010b
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getMd5("123", "asdfgh", 1024));
        System.out.println(getMd5("123", "F$CYGi"));
        System.out.println(getMd5("123"));
    }



}
