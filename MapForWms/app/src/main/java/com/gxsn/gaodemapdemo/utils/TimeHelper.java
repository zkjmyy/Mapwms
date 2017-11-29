package com.gxsn.gaodemapdemo.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by geo-compass on 2016/8/3.
 */
public class TimeHelper {

    /**
     * 时间格式转换
     * @param timeLong
     * @return
     */
    public static String TimeLong2String(long timeLong) {
        Date date = new Date(timeLong);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = formatter.format(date);
        return timeString;
    }
    /**
     * 时间格式转换
     * @param timeString
     * @return
     */
    public static long TimeString2Long(String timeString) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = formatter.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 得到当前时间
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String date_format = formatter.format(date);
        return date_format;
    }

    /**
     * 得到今天零点的时间
     *
     * @return
     */
    public static long getTodayZeroTime() {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) -
                TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        return zero;
    }
    /**
     * 得到昨天零点的时间
     *
     * @return
     */
    public static long getYesterdayZeroTime() {
        long current = System.currentTimeMillis();//当前时间毫秒数
        long zero = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) -
                TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        zero += 1000 * 3600 * 24;
        return zero;
    }

    /**
     * 得到任务截止的剩余时长
     * @param deadline
     * @return
     */
    public static Long getRemainTime(String deadline) {

        if(TextUtils.isEmpty(deadline))
            return 0L;
        long now = System.currentTimeMillis();
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = formatter.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long timeInterval = date.getTime() - now;
        return timeInterval;
    }
}
