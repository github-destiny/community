package cn.nero.community.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
public class ResponseUtil {

    public static Map<String, Object> getResponse(String msg, String status){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("status", status);
        return map;
    }

    public static Map<String, Object> getMap(){
        return new HashMap<>();
    }

    public static Map<String, Object> getMap(String key, Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
