package org.ls.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 获取日期
 */
public class DateUtils {

	public static String getNowTime1() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	public static String getNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
}