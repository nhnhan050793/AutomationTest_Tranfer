package A4_com.java.factory;

//public class a3_Toyota {
//	public void drive() {
//		System.out.println("driving Toyota");
//	}
//
//	public void view() {
//		System.out.println("viewing Toyota car");
//	}
//}

public class a3_Toyota extends a5_Car {

	@Override
	public void view() {
		// TODO Auto-generated method stub
		System.out.println("Viewing Toyota car");		
	}

	@Override
	public void driver() {
		// TODO Auto-generated method stub
		System.out.println("Driving Toyota car");	
	}
}
