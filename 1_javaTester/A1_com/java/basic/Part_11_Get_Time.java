package A1_com.java.basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Part_11_Get_Time {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		long start_time = System.currentTimeMillis();
		System.out.println(start_time);
		System.out.println(System.currentTimeMillis());
		System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));
		
		getTimeCST();
		getTimeCST();
		getTimeCST();
		getTimeCST();
		getTimeCST();
		
		Date date = new Date();
		System.out.println(date.toString());
		
	}
	public static void getTimeCST() {
		SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy h:mm a");
		format.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
		System.out.println(format.format(new Date()));
	}
}
