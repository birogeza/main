package com.bg.ticketwebshop.service;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeTransformator {

	public static Long convertDateTimeToLong(String date_time) {
		Long milliseconds;
		//String date_time = "09/30/2022 21:30:00";
		
		SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        {
            try {
                Date date = dateParser.parse(date_time);
                System.out.println(date);
                
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
                System.out.println(dateFormatter.format(date));
                milliseconds = date.getTime();
                System.out.println(milliseconds);
                return milliseconds;
                		
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }	  
	}
	
	
	public static String convertMillisToDateTime(long time){
	    Date date = new Date(time);
	    Format format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    return format.format(date);
	}
	
}
