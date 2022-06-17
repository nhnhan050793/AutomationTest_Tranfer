package A1_com.java.basic;

public class Part_02_Car {
	private String carName = "Honda";
	String fullName, firstName, lastName, emailErrorMessage;
	float productPrice;
	
	public void setCarName (String carName) {
		System.out.println(carName);
	}
	
	public String getCarName () {
		System.out.println(carName);
		return carName;
	}
	
	public String getEmailErrorMessage () {
		return null;
	}
	
}
