package com.gxsn.gaodemapdemo.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 */
public class DateUtils {
	/**
	 * 将long得到-- 小时:分
	 * @param lefttime
	 * @return 小时:分
	 */
	public static String formatTimeSimple(long lefttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
		String sDateTime = sdf.format(lefttime);
		return sDateTime;
	}
	public static String formatTime(long lefttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
		String sDateTime = sdf.format(lefttime);
		return sDateTime;
	}
	/**
	 * 得到: 年-月-日 小时:分钟
	 * @param lefttime
	 * @return 年-月-日 小时:分钟
	 */
	public static String formatDateAndTime(long lefttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
		String sDateTime = sdf.format(lefttime);
		return sDateTime;
	}

	/**
	 * 得到: 年-月-日 小时:分钟
	 * @param lefttime
	 * @return 年-月-日 小时:分钟
	 */
	public static String formatDateAndTimes(long lefttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		String sDateTime = sdf.format(lefttime);
		return sDateTime;
	}
	
	/**
	 * 得到: 年-月-日
	 * @param lefttime
	 * @return 年-月-日
	 */
	public static String formatDate(long lefttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String sDateTime = sdf.format(lefttime);
		return sDateTime;
	}
	
	/**
	 * 字符串转为long
	 * @param time 字符串时间,注意:格式要与template定义的一样
	 * @param template 要格式化的格式:如time为09:21:12那么template为"HH:mm:ss"
	 * @return long
	 */
	public static long formatToLong(String time, String template) {
		SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
		try {
			Date d = sdf.parse(time);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			long l = c.getTimeInMillis();
			return l;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 得到年份
	 * @param lefttime
	 * @return 得到年份
	 */
	public static int formatYear(long lefttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
		String sDateTime = sdf.format(lefttime);
		int i = Integer.parseInt(sDateTime);
		return i;
	}
	/**
	 * 得到月份
	 * @param lefttime
	 * @return 得到月份
	 */
	public static int formatMonth(long lefttime) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.CHINA);
		String sDateTime = sdf.format(lefttime);
		int i = Integer.parseInt(sDateTime);
		return i;
	}

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
	 * 两个时间之间的天数
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = 0;
		if (date != null && mydate != null){
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		}
		return day;
	}
}
