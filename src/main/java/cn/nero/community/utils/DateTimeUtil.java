package cn.nero.community.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/3/26
 */
public class DateTimeUtil {

    /**
     * 时间类工具,获取一个格式为 yyyy-MM-dd HH:mm:ss的时间
     * @return 19位时间
     */
    public static String getTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
