package com.liang.tech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	/* 
     * 将Unix时间戳转换为时间
     */
    public static String stampToDate(String s){

    	if(s!=null&&!"".equals(s)){
    		 String    formats = "yyyy-MM-dd HH:mm:ss";
    	        Long timestamp = Long.parseLong(s) * 1000;
    	        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
    	        return date;	
    	}else {
    		 return null;
		}

    }
    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Integer Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return (int) (sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2DateString(Integer seconds, String format) {
		if (seconds == null || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}
    
    /** 
     * 获取精确到秒的时间戳 (当前时间)
     * @return 
     */  
    public static int getSecondTimestamp(){  
         
        Date  date =new Date();
        String timestamp = String.valueOf(date.getTime());  
        int length = timestamp.length();  
        if (length > 3) {  
            return Integer.valueOf(timestamp.substring(0,length-3));  
        } else {  
            return 0;  
        }  
    }
    
    //获取当前时间，例如20171226
    public static String getDateToString(){
    	 	Date date=new Date();  
    	    SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMdd");  
    	    String time=formatter.format(date);  
    	    System.out.println(time);  
    	    return time;
    }
    //获取一年后时间，例如2017-12-26
    public static String getDateToStringAddOne(){
    	 	Date date=new Date();  
    	 	Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, +365);//+1今天的时间加一天
    	 	date = calendar.getTime();
    	    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  
    	    String time=formatter.format(date);  
    	    System.out.println(time);  
    	    return time;
    }
    //根据传入时间返回时间戳
    public static int getDayToTimes(String today){
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	
    	Date date = new Date();
		try {
			date = format.parse(today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return (int)(date.getTime()/1000);
    }
    public static int getTimesToMonth(int month){
    	Calendar calendar = Calendar.getInstance();// 获取当前日期  
	    calendar.set(Calendar.MONTH, month);  
	    calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天  
	    calendar.set(Calendar.HOUR_OF_DAY, 0);  
	    calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND, 0);  
		return (int) (calendar.getTimeInMillis()/1000);  
    }
    /**
     * 获取当前时间-yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getThisDate() {
    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         return df.format(new Date());// new Date()为获取当前系统时间
    }
}
