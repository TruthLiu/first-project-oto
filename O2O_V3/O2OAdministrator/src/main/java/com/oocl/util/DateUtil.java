package com.oocl.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date strToDate(String dateStr,String pattern) throws ParseException{
		DateFormat format = new SimpleDateFormat(pattern);
		return format.parse(dateStr);
	}
	
	public static String dateToStr(Date date,String pattern){
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
}
