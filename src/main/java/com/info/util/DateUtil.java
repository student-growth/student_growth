package com.info.util;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.validation.constraints.NotNull;

/**
 日期转换
 */
public class DateUtil {

	/**
	 yyyy-MM-dd HH:mm:ss
	 */
	public static String FORMAT_STYLE_1 = "yyyy-MM-dd HH:mm:ss";

	/**
	 yyyy-MM-dd
	 */
	public static String FORMAT_STYLE_2 = "yyyy-MM-dd";

	/**
	 yyyy年MM月dd日
	 */
	public static String FORMAT_STYLE_3 = "yyyy年MM月dd日";

	/**
	 yyyyMM
	 */
	public static String FORMAT_STYLE_4 = "yyyyMM";

	/**
	 yyyyMMdd
	 */
	public static String FORMAT_STYLE_5 = "yyyyMMdd";

	/**
	 yyyy-MM-dd HH:mm
	 */
	public static String FORMAT_STYLE_6 = "yyyy-MM-dd HH:mm";

	/**
	 yyyyMMddhhmmss
	 */
	public static String FORMAT_STYLE_7 = "yyyyMMddhhmmss";

	/**
	 yyMMdd
	 */
	public static String FORMAT_STYLE_8 = "yyMMdd";

	/**
	 yyMM
	 */
	public static String FORMAT_STYLE_9 = "yyMM";

	/**
	 yyyy-MM
	 */
	public static String FORMAT_STYLE_10 = "yyyy-MM";
	/**
	 * yyyyMMddhhmmssSSS
	 */
	public static String FORMAT_STYLE_11 = "yyyyMMddhhmmssSSS";
	/**
	 * yyyy
	 */
	public static String FORMAT_STYLE_12 = "yyyy";

	/** 周几 , 如果需要显示 "星期一" 直接用localdate的格式化就行 */
	static String[] weeks = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

	/**
	 将Date转换成String

	 @param date
	 @return
	 */
	public static String getString(Date date) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STYLE_1);
		return sdf.format(date);
	}

	/**
	 将当前时间转换成String

	 @param format
	 @return
	 */
	public static String getNowDateString(String format) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 将Date按一定的格式转成成String

	 @param date
	 @param format
	 @return
	 */
	public static String getStringDate(Date date, String format) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 将Date转换成String

	 @param date
	 @param format
	 @return
	 */
	public static String getString(Date date, String format) {
		// 日期格式
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 字符转成日期

	 @param dateString
	 @return
	 @throws ParseException
	 */
	public static Date getDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STYLE_1);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 字符转成日期

	 @param dateString
	 @param format
	 @return
	 @throws ParseException
	 */
	public static Date getDate(String dateString, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 获取系统当前时间戳String

	 @return
	 */
	public static String getCurrentTimeMillis2String() {
		return new Long(System.currentTimeMillis()).toString();
	}

	/**
	 获取系统当前时间----Date

	 @return Date
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 获取系统当前时间----Timestamp

	 @return
	 */
	public static Timestamp getNowTimestamp() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		Timestamp buydate = Timestamp.valueOf(nowTime);
		return buydate;
	}

	/**
	 @param mins
	 @return 根据指定分钟数获取距离当前时间的Date时间 -mins 指前几分钟， +mins 后几分钟
	 */
	@SuppressWarnings("deprecation")
	public static Date getSystemTime4AppointMins(int mins) {
		Date nowDate = getNowDate();
		nowDate.setMinutes(nowDate.getMinutes() + mins);
		return nowDate;
	}

	/**
	 @param hours
	 @return 根据指定小时数获取距离当前时间的Date时间 -hours 指前几小时， +hours 后几小时
	 */
	@SuppressWarnings("deprecation")
	public static Date getSystemTime4AppointHours(int hours) {
		Date nowDate = getNowDate();
		nowDate.setHours(nowDate.getHours() + hours);
		return nowDate;
	}

	/**
	 @param days
	 @return 根据指定小时数获取距离当前时间的Date时间 -days 指前几天， +days 后几天
	 */
	@SuppressWarnings("deprecation")
	public static Date getSystemTime4AppointDays(int days) {
		Date nowDate = getNowDate();
		nowDate.setDate(nowDate.getDate() + days);
		return nowDate;
	}

	/**
	 获取2个时间之间的分钟差

	 @param startday
	 @param endday
	 @return
	 */
	public static int getIntervalMins(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60));
	}

	/**
	 获取2个时间之间的天数差

	 @param startday
	 @param endday
	 @return
	 */
	public static int getIntervalDays(Timestamp startday, Timestamp endday) {
		if (startday.after(endday)) {
			Timestamp cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 获取2个时间之间的天数差,通过localdate 来获取, 相对上一个方法要简单些

	 @param startDate
	 @param endDate
	 @return
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		return (int) ChronoUnit.DAYS.between(date2LocalDate(startDate), date2LocalDate(endDate));
	}

	/**
	 获取2个时间之间的天数差,

	 @param startDate
	 @param endDate
	 @return
	 */
	public static int getIntervalDays(LocalDate startDate, LocalDate endDate) {
		return (int) ChronoUnit.DAYS.between(startDate, endDate);
	}

	/**
	 获取2个时间之间的小时差

	 @param startday
	 @param endday
	 @return
	 */
	public static int getIntervalHours(Timestamp startday, Timestamp endday) {
		if (startday.after(endday)) {
			Timestamp cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60));
	}

	public static float getIntervalHours_float(Timestamp startday, Timestamp endday) {
		if (startday.after(endday)) {
			Timestamp cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (float) (ei / (1000 * 60 * 60));
	}

	/**
	 返回某天的0点
	 */
	public static Timestamp getZeroTime(Timestamp time) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 返回某天的23点
	 */
	public static Timestamp getBigTime(Timestamp time) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 当前时间的字符串

	 @return
	 */
	public static String getNowTimeString() {
		return getString(getNowDate());
	}

	public static Timestamp getTimestampByStr(String time) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(time);
			return ts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 获取相差指定日期

	 @param time ：指定日期
	 @param days ： days可正可负，负为指定日期前，正为指定日期后
	 @return
	 */
	public static Timestamp getTimeAdd(Timestamp time, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 获取距离指定时间的指定日期

	 @param date 指定时间
	 @param days days可正可负，负为指定日期前，正为指定日期后
	 @return
	 */
	public static Date getDayChange(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	/**
	 获取相差指定日期

	 @param time   ：指定日期
	 @param months ： days可正可负，负为指定日期前，正为指定日期后
	 @return
	 */
	public static Timestamp getMonthAdd(Timestamp time, int months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(time);
		cal.add(Calendar.MONTH, months);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 判断给定日期是否已经超时 判断年，月，日

	 @param time ：指定日期
	 @return 超时=true，未超时=false
	 */
	public static Boolean judgeTime(Timestamp time) {
		Timestamp nowTime = new Timestamp((new Date()).getTime());
		nowTime = getBigTime(nowTime);
		time = getBigTime(time);
		/*
		 * GregorianCalendar calStart = new GregorianCalendar();
		 * calStart.setTime(time); GregorianCalendar calNow = new
		 * GregorianCalendar(); calNow.setTime(nowTime);
		 */
		return nowTime.after(time);
	}

	/**
	 判断给定日期是否已经超时 判断年，月，日

	 @param date
	 @return
	 */
	public static Boolean judgeTimeByDate(Date date) {
		Date now = getNowDate();
		return now.after(date);
	}

	/**
	 获得上月1号的日期
	 */
	public static Date getLastMonthBeginDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 获得上个月最后一天的日期
	 */
	public static Date getLastMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 获得这个月最后一天的日期
	 */
	public static Date getThisMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		// 当月最大天数
		int a = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, a);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 获得时间中年，月，日单个值
	 */
	public static int getMonthFromDate(Date date, int type) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		int monthNow = cal.get(type);

		return monthNow;
	}

	/**
	 获取年龄

	 @param year
	 @param month
	 @param day
	 @return
	 */
	public static int getAge(int year, int month, int day) {
		try {
			// 年龄
			int age = getNowYear();
			age = age - year;
			// 日本年龄满一年才是一岁
			age = age - 1;
			if (0 > age) {
				age = 0;
			}
			if (getNowMonth() > month) {
				// 当前月大于出生月，年龄加1
				age++;
			} else if (getNowMonth() == month) {
				// 当前日等于出生月,并且日大于生日
				if (getNowDay() > day) {
					age++;
				}
			}
			return age;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 取得当前年

	 @return
	 */
	public static int getNowYear() {
		try {
			// 当前月
			return Calendar.getInstance().get(Calendar.YEAR);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 取得当前月

	 @return
	 */
	public static int getNowMonth() {
		try {
			// 当前月
			return Calendar.getInstance().get(Calendar.MONTH) + 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 取得当前日

	 @return
	 */
	public static int getNowDay() {
		try {
			// 当前日
			return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 现在距离指定时间还有多少小时

	 @param appointedTime
	 @return
	 */
	public static int getHourByAppointedTime(Date appointedTime) {
		long now = getNowDate().getTime();
		long at = appointedTime.getTime();
		long ei = at - now;
		return (int) (ei / (1000 * 60 * 60));
	}

	/**
	 与现在的时间相差几分钟

	 @param appointedTime
	 @return
	 */
	public static int getminsByAppointedTime(Date appointedTime) {
		long now = getNowDate().getTime();
		long at = appointedTime.getTime();
		long ei = now - at;
		return (int) (ei / (1000 * 60));
	}

	/**
	 与现在的时间相差几分钟

	 @param appointedTime
	 @return
	 */
	public static int getminsByAppointedTimestamp(Timestamp appointedTime) {
		long now = getNowDate().getTime();
		long at = appointedTime.getTime();
		long ei = now - at;
		return (int) (ei / (1000 * 60));
	}

	public static String formatToYYYYMMDD(Date date) {
		if (date == null) {
			return "";
		}
		String result = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			result = format.format(date);
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	public static String formatToYYYYMMDD2(Date date) {
		if (date == null) {
			return "";
		}
		String result = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat(FORMAT_STYLE_5);
			result = format.format(date);
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	public static Date parseDate(String dateStr, String pattern) {
		Date date = null;
		if (!StringUtils.isEmpty(dateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
			}
		}
		return date;
	}

	public static Date addMonth(String dateStr, int month) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		Calendar cal = null;
		try {
			date = df.parse(dateStr);
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, month);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return cal.getTime();
	}

	/**
	 localDate转Date
	 */
	public static Date localDate2Date(LocalDate localDate) {
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		Instant instant1 = zonedDateTime.toInstant();
		Date from = Date.from(instant1);
		return from;
	}

	/**
	 localDateTime转Date
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
		Instant instant1 = zonedDateTime.toInstant();
		Date from = Date.from(instant1);
		return from;
	}

	/**
	 Date 转 localDate
	 */
	public static LocalDate date2LocalDate(Date date) {
		Instant instant = date.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate localDate = zdt.toLocalDate();
		return localDate;
	}

	/**
	 Date 转 localDateTime
	 */
	public static LocalDateTime date2LocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDateTime localDateTime = zdt.toLocalDateTime();
		return localDateTime;
	}

	//获取月第一天
	public static Date getStartDayOfMonth(String date) {
		LocalDate now = LocalDate.parse(date);
		return getStartDayOfMonth(now);
	}

	public static Date getStartDayOfMonth(LocalDate date) {
		LocalDate now = date.with(TemporalAdjusters.firstDayOfMonth());
		return localDate2Date(now);
	}

	//获取月最后一天
	public static Date getEndDayOfMonth(String date) {
		LocalDate localDate = LocalDate.parse(date);
		return getEndDayOfMonth(localDate);
	}

	public static Date getEndDayOfMonth(LocalDate date) {
		LocalDate now = date.with(TemporalAdjusters.lastDayOfMonth());

		Date.from(now.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
		return localDate2Date(now);
	}

	//获取周第一天
	public static Date getStartDayOfWeek(String date) {
		LocalDate now = LocalDate.parse(date);
		return getStartDayOfWeek(now);
	}

	public static Date getStartDayOfWeek(TemporalAccessor date) {
		TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
		LocalDate localDate = LocalDate.from(date);
		localDate = localDate.with(fieldISO, 1);
		return localDate2Date(localDate);
	}

	//获取周最后一天
	public static Date getEndDayOfWeek(String date) {
		LocalDate localDate = LocalDate.parse(date);
		return getEndDayOfWeek(localDate);
	}

	public static Date getEndDayOfWeek(TemporalAccessor date) {
		TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
		LocalDate localDate = LocalDate.from(date);
		localDate = localDate.with(fieldISO, 7);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
	}

	//一天的开始
	public static Date getStartOfDay(String date) {
		LocalDate localDate = LocalDate.parse(date);
		return getStartOfDay(localDate);
	}

	//一天的开始
	public static Date getStartOfDay(Date date) {
		LocalDate localDate = DateUtil.date2LocalDate(date);
		return getStartOfDay(localDate);
	}

	public static Date getStartOfDay(TemporalAccessor date) {
		LocalDate localDate = LocalDate.from(date);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	//一天的结束
	public static Date getEndOfDay(String date) {
		LocalDate localDate = LocalDate.parse(date);
		return getEndOfDay(localDate);
	}

	//一天的结束
	public static Date getEndOfDay(Date date) {
		LocalDate localDate = date2LocalDate(date);
		return getEndOfDay(localDate);
	}

	public static Date getEndOfDay(TemporalAccessor date) {
		LocalDate localDate = LocalDate.from(date);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
	}

	public static Date getTomorrow() {
		LocalDate localDate = date2LocalDate(new Date()).plusDays(1);
		return localDate2Date(localDate);
	}

	public static Date getYestoday() {
		LocalDate localDate = date2LocalDate(new Date()).minusDays(1);
		return localDate2Date(localDate);
	}

	/**
	 获取 当前为周几, 显示为"周一"

	 @param date
	 @return
	 */
	public static String getWeekString(LocalDate date) {
		int value = date.getDayOfWeek().getValue();
		return weeks[value - 1];
	}

	/**
	 获取 当前为星期几, 显示为"星期一"

	 @param date
	 @return
	 */
	public static String getWeekString2(LocalDate date) {
		return date.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA);
	}

	/**
	 两个时间之间相差距离多少天

	 @param one 时间参数 1：
	 @param two 时间参数 2：
	 @return 相差天数
	 */
	public static long getDistanceDays(Date one, Date two) throws Exception {
		long days = 0;
		long time1 = one.getTime();
		long time2 = two.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		days = diff / (1000 * 60 * 60 * 24);
		return days;
	}

	/**
	 两个时间相差距离多少天多少小时多少分多少秒

	 @param one
	 @param two
	 @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(Date one, Date two) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		long time1 = one.getTime();
		long time2 = two.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long[] times = {day, hour, min, sec};
		return times;
	}

	/**
	 两个时间相差距离多少天多少小时多少分多少秒

	 @param one
	 @param two
	 @return String 返回值为：xx天xx小时xx分xx秒
	 */
	public static String getDistanceTime(Date one, Date two) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		long time1 = one.getTime();
		long time2 = two.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		day = diff / (24 * 60 * 60 * 1000);
		hour = (diff / (60 * 60 * 1000) - day * 24);
		min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return day + "天" + hour + "小时" + min + "分" + sec + "秒";
	}


	/**
	 * 如果2个都是null，返回null；
	 * <p> 如果其中有一个为null，返回另外一个不为null的；
	 * <p> 如果两个相等，返回date1；
	 * <p> 返回小的
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date compareAndGetMin(Date date1, Date date2) {
		if (date1 == null && date2 == null) return null;
		else if (date1 == null) return date2;
		else if (date2 == null) return date1;
		else if (date1.before(date2) || date1.equals(date2)) return date1;
		else return date2;
	}

	/**
	 * 如果2个都是null，返回null；
	 * <p> 如果其中有一个为null，返回另外一个不为null的；
	 * <p> 如果两个相等，返回date1；
	 * <p> 返回小的
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static LocalDate compareAndGetMin(LocalDate date1, LocalDate date2) {
		if (date1 == null && date2 == null) return null;
		else if (date1 == null) return date2;
		else if (date2 == null) return date1;
		else if (date1.isBefore(date2) || date1.equals(date2)) return date1;
		else return date2;
	}

	/**
	 * 通过对比1970.1.1起的月份数，来获得2个日期之间的月份差。
	 * <p> 此方法避免了直接使用ChronoUnit.MONTHS.between(start, end) 或 Period.between(start, end).getMonths() 的问题：
	 * <p> 用between计算出来的是按日期天数算出来的月数，也就是说2个日期相差不到30天，则月数为0，即使他们是在不同的月份。
	 *
	 * <p> 用EpochDay的方式，会准确计算出2个日期的月份差。比如，2019.05.30 到 2019.06.1之间的月份差为1。
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalMonths(@NotNull Date start, @NotNull Date end) {
		return getIntervalMonths(DateUtil.date2LocalDate(start), DateUtil.date2LocalDate(end));
	}

	/**
	 * 通过对比1970.1.1起的月份数，来获得2个日期之间的月份差。
	 * <p> 此方法避免了直接使用ChronoUnit.MONTHS.between(start, end) 或 Period.between(start, end).getMonths() 的问题：
	 * <p> 用between计算出来的是按日期天数算出来的月数，也就是说2个日期相差不到30天，则月数为0，即使他们是在不同的月份。
	 *
	 * <p> 用EpochDay的方式，会准确计算出2个日期的月份差。比如，2019.05.30 到 2019.06.1之间的月份差为1。
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getIntervalMonths(@NotNull LocalDate start, @NotNull LocalDate end) {
		LocalDate epoch = LocalDate.ofEpochDay(0);
		long startMonths = Period.between(epoch, start).toTotalMonths();
		long endMonths = Period.between(epoch, end).toTotalMonths();
		return (int) (endMonths - startMonths);
	}

	/**
	 * 判断2个日期是否相等，如果其中一个为null，则为false。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(Date date1, Date date2) {
		if (date1 == null || date2 == null) return false;
		return date1.equals(date2);
	}

	/**
	 * 判断2个日期是否相等，如果其中一个为null，则为false。
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(ChronoLocalDate date1, ChronoLocalDate date2) {
		if (date1 == null || date2 == null) return false;
		return date1.equals(date2);
	}

	public static void main(String[] args) {
		System.out.println(getNowTimeString());
	}
}
