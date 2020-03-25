package me.galaxy.archetype.infra.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author galaxy-captain
 * @Date 2020/3/25 11:22 下午
 **/
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static Date getNow() {
        return new Date();
    }

    public static long getInterval(Date date) {
        return getInterval(date, getNow());
    }

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