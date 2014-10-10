package com.rae.core.alarm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public final class AlarmUtils {
	public static Calendar calendar = Calendar.getInstance();
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * ��ȡ���ڵĳ����ͣ���ȡʧ�ܷ���ϵͳ��ǰʱ�䡣
	 * 
	 * @param date
	 *            ʱ��
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static long getTimeInMillis(String date) {
		Date currentDate;
		try {
			String format = "HH:mm";
			if (date.contains("-")) {
				format = "yyyy-MM-dd HH:mm:ss";
			}
			SimpleDateFormat dateForamt = new SimpleDateFormat(format);
			currentDate = dateForamt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();

			currentDate = new Date();
		}
		calendar.setTime(currentDate);
		return calendar.getTimeInMillis();
	}

	public static String getDateByTimeInMillis(long milliseconds) {
		calendar.setTimeInMillis(milliseconds);
		return dateToString(calendar.getTime());
	}

	/**
	 * �ַ���ת����
	 * 
	 * @param date
	 *            ���ڣ���ʽ2014-12-12 16:00:00
	 * @return
	 */
	public static Date parseDate(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * ����ת�ַ���
	 * 
	 * @param date
	 *            ����
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(DEFAULT_DATE_FORMAT, date);
	}

	/**
	 * ����ת�ַ���
	 * 
	 * @param format
	 *            ��ʽ
	 * @param date����
	 * @return
	 */
	public static String dateToString(String format, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * ��ȡ��һ�����ܼ���
	 * 
	 * @return
	 */
	public static int getDayOfWeek(long time) {
		calendar.clear();
		calendar.setTimeInMillis(time);
		int result = calendar.get(Calendar.DAY_OF_WEEK) - 1; // ������������Ϊ��һ��ģ������ȥ1
		if (result <= 0) {
			result = 7;
		}
		return result;
	}

}
