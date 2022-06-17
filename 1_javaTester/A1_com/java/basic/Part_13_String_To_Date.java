package A1_com.java.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Part_13_String_To_Date {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String today = "Mar 23, 2011";
		
		System.out.println(convertStringToDate(today));
	}
	
	public static Date convertStringToDate(String dateInString) {
		System.out.println("Before: " + dateInString);
		dateInString = dateInString.replace(",", "");
		System.out.println("After: " + dateInString);
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
