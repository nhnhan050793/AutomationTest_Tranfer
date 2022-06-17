package A1_com.java.basic;

import java.util.Random;

public class Part_07_Random_Data {
	public static void main(String[] args) {
		Part_07_Random_Data part = new Part_07_Random_Data();
		System.out.println("nhan" + part.randomNumber() + "@hotmail.com" );
		System.out.println("nhan" + part.randomNumber() + "@hotmail.com" );
		System.out.println("nhan" + part.randomNumber() + "@hotmail.com" );
		System.out.println("nhan" + part.randomNumber() + "@hotmail.com" );
		System.out.println("nhan" + part.randomNumber() + "@hotmail.com" );
		System.out.println("nhan" + part.randomNumber() + "@hotmail.com" );
	}
		public int randomNumber() {
			Random rand = new Random();
			return rand.nextInt(999999);	
	}
}