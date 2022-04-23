package cn.nero.community.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static Date getSpecifiedDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    private static String getPreDay(Calendar calendar){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取某个时间的几天前
     * @param date
     * @param day
     * @return
     * @throws ParseException
     */
    public static String getPreDay(String date, Integer day) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(getSpecifiedDate(date));
        instance.add(Calendar.DAY_OF_MONTH, day);
        return getPreDay(instance);
    }

    /**
     * 获取当前天数的几天前
     * @param day
     * @return
     * @throws ParseException
     */
    public static String getPreDay(Integer day) throws ParseException {
        Date date = new Date();
        return getPreDay(date.toString(), day);
    }

}
