package me.galaxy.archetype.infra.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 日期时间工具类
 * @Author galaxy-captain
 * @Date 2020/3/25 11:22 下午
 **/
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 获取当前日期时间
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 获取某日期到当前之间的天数
     *
     * @param date 起始日期
     */
    public static long getInterval(Date date) {
        return getInterval(date, getNow());
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param beginDate 起始日期
     * @param endDate   结束日期
     */
    public static long getInterval(Date beginDate, Date endDate) {

        if (beginDate == null || endDate == null) {
            throw new NullPointerException();
        }

        long day = 0;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String begin = sdf.format(beginDate);
            beginDate = sdf.parse(begin);
            String end = sdf.format(endDate);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

        day = Math.abs(endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);

        return day;
    }

}