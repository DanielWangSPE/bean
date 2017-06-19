package com.wz.test.mode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
* 
* @author : wangzhi
* @data ：2017年6月8日 上午8:51:52 
* 
*/
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*SingleTon s1 = SingleTon.getInstance();
		SingleTon s2 = SingleTon.getInstance();
		System.out.println(s1 == s2);
		s1.set(1);
		s2.set(2);
		System.out.println(s1.get());
		System.out.println(s2.get());*/
		
		System.out.println(System.currentTimeMillis());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		Date date;
		try {
			date = df.parse("2017-06-13 12-00-00");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			System.out.println(cal.getTimeInMillis());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
