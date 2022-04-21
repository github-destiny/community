package cn.nero.community.utils;

import java.util.UUID;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/19
 */
public class UUIDUtil {

    public static String getUUID(int len){
        return UUID.randomUUID().toString().replace("-", "").substring(0, len);
    }

    public static String getUUID(){
        return getUUID(6);
    }

    public static String getNickName(){
        return "u-" + getUUID();
    }

}
