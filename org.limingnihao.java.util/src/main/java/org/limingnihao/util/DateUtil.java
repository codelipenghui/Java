package org.limingnihao.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.limingnihao.util.model.DateBean;
import org.limingnihao.util.model.WeekBean;

/**
 * Date常用方法
 * 
 * @author 黎明你好
 * 
 */
public class DateUtil {

	public static String defaultDateTimeFormatString = "yyyy-MM-dd HH:mm:ss";
	public static String defaultDateFormatString = "yyyy-MM-dd";
	public static String defaultTimeFormatString = "HH:mm:ss";

	public static void main(String args[]) {
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 1970);
		start.set(Calendar.MONTH, 0);
		start.set(Calendar.DAY_OF_MONTH, 1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		start.add(Calendar.SECOND, 1409635994);

		System.out.println("" + format(start.getTime()));
	}

	public static Date getDefaultStartDate() {
		return DateUtil.parse("1900-01-01");
	}

	public static Date getDefaultEndDate() {
		return new Date();
	}

	/**
	 * 当天日期 0时0分0秒
	 * 
	 * @return
	 */
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 当天的 - 昨天日期0时0分0秒
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 某天的 - 昨天日期0时0分0秒
	 * 
	 * @return
	 */
	public static Date getYesterday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 当前的 - 明天日期0时0分0秒
	 * 
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 某天的 - 明天日期0时0分0秒
	 * 
	 * @return
	 */
	public static Date getTomorrow(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 将字符串转换为时间类型，按照yyyy-MM-dd 或yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date parse(String source) {
		if (source == null || "".equals(source)) {
			return null;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat();
			if (source.indexOf(":") > 0) {
				format.applyPattern(defaultDateTimeFormatString);
			} else {
				format.applyPattern(defaultDateFormatString);
			}
			return format.parse(source.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将字符串转换为时间类型，按照指定的输入格式
	 * 
	 * @param String
	 * @return Date
	 */
	public static Date parse(String source, String formatString) {
		if (source == null || "".equals(source)) {
			return null;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat();
			format.applyPattern(formatString.trim());
			return format.parse(source.trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将时间转换为格式为 yyyy-MM-dd HH:mm:ss 的字符串
	 * 
	 * @param Date
	 * @return String
	 */
	public static String format(Timestamp timestamp) {
		if (timestamp != null) {
			return format(new Date(timestamp.getTime()), defaultDateTimeFormatString);
		}
		return "";
	}

	/**
	 * 将时间转换为格式为 yyyy-MM-dd HH:mm:ss 的字符串
	 * 
	 * @param Date
	 * @return String
	 */
	public static String format(Timestamp timestamp, String formatString) {
		if (timestamp != null) {
			return format(new Date(timestamp.getTime()), formatString);
		}
		return "";
	}

	/**
	 * 将时间转换为格式为 yyyy-MM-dd HH:mm:ss 的字符串
	 * 
	 * @param Date
	 * @return String
	 */
	public static String format(Date date) {
		return format(date, defaultDateTimeFormatString);
	}

	/**
	 * 将时间转换为字符串，按照指定的格式
	 * 
	 * @param Date
	 * @param formatString
	 * @return String
	 */
	public static String format(Date date, String formatString) {
		try {
			if (date != null) {
				SimpleDateFormat format = new SimpleDateFormat(formatString);
				return format.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 随机生成一个时间
	 * 
	 * @return
	 */
	public static Date randomDate() {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		Calendar resultCalendar = Calendar.getInstance();
		startCalendar.setTime(getDefaultStartDate());
		endCalendar.setTime(getDefaultEndDate());
		long mills = (long) (Math.random() * (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis())) + startCalendar.getTimeInMillis();
		resultCalendar.setTimeInMillis(mills);
		return resultCalendar.getTime();
	}

	/**
	 * 在一个时间段内，随机生成一个时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Date randomDate(Date startDate, Date endDate) {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		Calendar resultCalendar = Calendar.getInstance();
		startCalendar.setTime(getDefaultStartDate());
		endCalendar.setTime(getDefaultEndDate());
		if (startDate != null) {
			startCalendar.setTime(startDate);
		}
		if (endDate != null) {
			endCalendar.setTime(endDate);
		}
		long mills = (long) (Math.random() * (endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis())) + startCalendar.getTimeInMillis();
		resultCalendar.setTimeInMillis(mills);
		return resultCalendar.getTime();
	}

	/**
	 * 获取本周对象 - 当天
	 * 
	 * @param Date
	 * @return WeekBean
	 */
	public static WeekBean getWeekBean(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar start = Calendar.getInstance();
		start.setFirstDayOfWeek(Calendar.MONDAY);
		start.setTime(calendar.getTime());
		start.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		Calendar end = Calendar.getInstance();
		end.setFirstDayOfWeek(Calendar.MONDAY);
		end.setTime(start.getTime());
		end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		WeekBean weekBean = new WeekBean();
		weekBean.setStartDate(start.getTime());
		weekBean.setEndDate(end.getTime());
		if (calendar.get(Calendar.MONTH) == start.get(Calendar.MONTH)) {
			weekBean.setWeekId(start.get(Calendar.WEEK_OF_MONTH));
		} else {
			weekBean.setWeekId(end.get(Calendar.WEEK_OF_MONTH));
		}
		// System.out.println(format(calendar.getTime()) + " - " + format(start.getTime()) + " - " + format(end.getTime()) + ", weekId=" + weekBean.getWeekId());
		return weekBean;
	}

	/**
	 * 获取周列表 - 当月
	 * 
	 * @param Date
	 * @return List<WeekBean>
	 */
	public static List<WeekBean> getWeekList(Date date) {
		List<WeekBean> list = new ArrayList<WeekBean>();
		Calendar start = Calendar.getInstance();
		start.setTime(date);
		start.set(Calendar.DAY_OF_MONTH, 1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		// System.out.println("本月第一天=" + format(start.getTime()));

		Calendar end = Calendar.getInstance();
		end.setTime(start.getTime());
		end.add(Calendar.MONTH, 1);
		end.add(Calendar.DAY_OF_MONTH, -1);
		// System.out.println("本月最后天=" + format(end.getTime()));

		for (int i = 0; start.getTime().getTime() <= end.getTime().getTime(); i++, start.add(Calendar.DAY_OF_MONTH, 1)) {
			if (i % 7 == 0 || i % 30 == 0) {
				boolean isHave = false;
				WeekBean weekNew = getWeekBean(start.getTime());
				for (WeekBean bean : list) {
					if (bean.getWeekId() == weekNew.getWeekId()) {
						isHave = true;
						break;
					}
				}
				if (!isHave) {
					// System.out.println(format(weekNew.getStartDate()) + " - " + format(weekNew.getEndDate()) + "-" + weekNew.getWeekId());
					list.add(weekNew);
				}
			}
		}
		return list;
	}

	/**
	 * 获取本周日期列表
	 * 
	 * @param Date
	 * @return List<DateBean>
	 */
	public static List<DateBean> getDateListBySame(Date date) {
		List<DateBean> list = new ArrayList<DateBean>();
		WeekBean weekBean = getWeekBean(date);
		Calendar start = Calendar.getInstance();
		start.setTime(weekBean.getStartDate());
		list.add(getDateBean(start.getTime()));
		// System.out.println(format(start.getTime()));
		while (start.getTime().getTime() < weekBean.getEndDate().getTime()) {
			start.add(Calendar.DAY_OF_MONTH, 1);
			// System.out.println(format(start.getTime()));
			list.add(getDateBean(start.getTime()));
		}
		return list;
	}

	/**
	 * 获取上周日期列表
	 * 
	 * @param dateBean
	 * @return List<DateBean>
	 */
	public static List<DateBean> getDateListByPrevious(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		return getDateListBySame(calendar.getTime());
	}

	/**
	 * 获取下周日期列表
	 * 
	 * @param dateBean
	 * @return List<DateBean>
	 */
	public static List<DateBean> getDateListByNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		return getDateListBySame(calendar.getTime());
	}

	/**
	 * 获取时间Bean
	 * 
	 * @param Date
	 * @return DateBean
	 */
	public static DateBean getDateBean(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		DateBean bean = new DateBean();
		bean.setDate(date);
		bean.setYear(calendar.get(Calendar.YEAR));
		bean.setMonth(calendar.get(Calendar.MONTH) + 1);
		bean.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		bean.setHour(calendar.get(Calendar.HOUR_OF_DAY));
		bean.setMinute(calendar.get(Calendar.MINUTE));
		bean.setSecond(calendar.get(Calendar.SECOND));
		bean.setWeek(calendar.get(Calendar.DAY_OF_WEEK) - 1);
		bean.setWeek(bean.getWeek() == 0 ? 7 : bean.getWeek());
		String weekName = "";
		switch (bean.getWeek()) {
		case 1:
			weekName = "星期一";
			break;
		case 2:
			weekName = "星期二";
			break;
		case 3:
			weekName = "星期三";
			break;
		case 4:
			weekName = "星期四";
			break;
		case 5:
			weekName = "星期五";
			break;
		case 6:
			weekName = "星期六";
			break;
		case 7:
			weekName = "星期日";
			break;
		}
		bean.setWeekName(weekName);
		return bean;
	}

	/**
	 * 当前时间bean形式
	 * 
	 * @return
	 */
	public static DateBean getCurrentDateBean() {
		return getDateBean(new Date());
	}

	/**
	 * 在指定时间上，增加一个时间戳
	 * 
	 * @param date
	 * @param calendarField
	 * @param amount
	 * @return
	 */
	private static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	public static Date addMilliseconds(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

}
