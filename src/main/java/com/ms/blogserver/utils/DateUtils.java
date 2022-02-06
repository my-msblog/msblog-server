package com.ms.blogserver.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/2
 */
public class DateUtils {

    public static String now() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR)+"-"
                +calendar.get(Calendar.MONTH)+"-"
                +calendar.get(Calendar.DATE)+" "
                +calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取当天初始时间
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天结束时间
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartTime(date));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    /**
     * 获取前 days 天前的时间
     * @param date
     * @param days
     * @return
     */
    public static Date minusTimeForDay(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 一周前
     * @param date
     * @return
     */
    public static Date getBeforeWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, - 7);
        return calendar.getTime();
    }

    /**
     * 一个月前
     * @param date
     * @return
     */
    public static Date getBeforeMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 一年前
     * @param date
     * @return
     */
    public static Date getBeforeYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTime();
    }
}
