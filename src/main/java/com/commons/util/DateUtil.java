/**
 * @Title: DateUtil.java
 * @Package com.util
 * @Description: TODO
 * @author Du.Jun
 * @date 2015年11月23日 下午6:45:31
 * @version V1.0
 */

package com.commons.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Du.Jun
 *
 */
public class DateUtil {

	/**
	 * 获取前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecifiedDayBefore(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return c.getTime();
	}

	/**
	 * 获取前num天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecifiedDayBefore(Date date, Integer num) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - num);
		return c.getTime();
	}

	/**
	 * 获取后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecifiedDayAfter(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		int mi = c.get(Calendar.MILLISECOND);
		c.set(Calendar.DATE, day + 1);
		c.set(Calendar.MILLISECOND, mi - 1);
		return c.getTime();
	}

	/**
	 * 获取后num天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecifiedDayAfter(Date date, Integer num) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + num);
		return c.getTime();
	}

	/**
	 * 获取后一个月
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecifiedMonthAfter(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		int mi = c.get(Calendar.MILLISECOND);
		c.set(Calendar.MONTH, month + 1);
		c.set(Calendar.MILLISECOND, mi - 1);
		return c.getTime();
	}

	/**
	 * 获取后num月
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date getSpecifiedMonthAfter(Date date, Integer num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		int mi = c.get(Calendar.MILLISECOND);
		c.set(Calendar.MONTH, month + num);
		c.set(Calendar.MILLISECOND, mi - 1);
		return c.getTime();
	}

	/**
	 * 获取后一年
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecifiedYearAfter(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int mi = c.get(Calendar.MILLISECOND);
		c.set(Calendar.YEAR, year + 1);
		c.set(Calendar.MILLISECOND, mi - 1);
		return c.getTime();
	}

	/**
	 * 获取后num月
	 * 
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date getSpecifiedYearAfter(Date date, Integer num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int mi = c.get(Calendar.MILLISECOND);
		c.set(Calendar.YEAR, year + num);
		c.set(Calendar.MILLISECOND, mi - 1);
		return c.getTime();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("当前时间：" + new Date().toLocaleString());
		System.out.println("当天0点时间：" + getTimesmorning().toLocaleString());
		System.out.println("当天24点时间：" + getTimesnight().toLocaleString());
		System.out.println("本周周一0点时间：" + getTimesWeekmorning().toLocaleString());
		System.out.println("本周周日24点时间：" + getTimesWeeknight().toLocaleString());
		System.out.println("本月初0点时间：" + getTimesMonthmorning().toLocaleString());
		System.out.println("本月未24点时间：" + getTimesMonthnight().toLocaleString());

		System.out.println("上周周周一0点时间：" + getTimesLastWeekmorning().toLocaleString());
		System.out.println("上周周日24点时间：" + getTimesLastWeeknight().toLocaleString());
		System.out.println("上月初0点时间：" + getTimesLastMonthmorning().toLocaleString());
		System.out.println("上月未24点时间：" + getTimesLastMonthnight().toLocaleString());
	}

	// 获得当天0点时间
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获得当天24点时间
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获得本周一0点时间
	public static Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	// 获得本周日24点时间
	public static Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}

	// 获得本月第一天0点时间
	public static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	// 获得本月最后一天24点时间
	public static Date getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
	}

	// 获得上周一0点时间
	public static Date getTimesLastWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH) - 7, 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	// 获得上周日24点时间
	public static Date getTimesLastWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesLastWeekmorning());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}

	// 获得上月第一天0点时间
	public static Date getTimesLastMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY) - 1, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	// 获得上月最后一天24点时间
	public static Date getTimesLastMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY) - 1, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
	}

	/**
	 * 得到指定月的天数
	 */
	public static int getMonthLastDay(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到指定季度的天数
	 */
	public static int getSeasonLastDay(int year, int season) {
		if (season == 1) {
			return getMonthLastDay(year, 1) + getMonthLastDay(year, 2) + getMonthLastDay(year, 3);
		}
		if (season == 2) {
			return getMonthLastDay(year, 4) + getMonthLastDay(year, 5) + getMonthLastDay(year, 6);
		}
		if (season == 3) {
			return getMonthLastDay(year, 7) + getMonthLastDay(year, 8) + getMonthLastDay(year, 9);
		}
		if (season == 4) {
			return getMonthLastDay(year, 10) + getMonthLastDay(year, 11) + getMonthLastDay(year, 12);
		}
		return 0;
	}

	/**
	 * 判断时间季度
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}
}
