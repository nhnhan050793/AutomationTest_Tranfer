package A4_com.java.factory;

//public class a4_User {
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// View and driver honda car
//		a1_Honda  honda = new a1_Honda ();
//		honda .view();
//		honda .drive();
//
//		// View and driver Toyota car
//		a3_Toyota toyota = new a3_Toyota();
//		toyota.view();
//		toyota.drive();
//
//		// view and driver Huyndai car
//		a2_Huyndai huyndai = new a2_Huyndai();
//		huyndai.view();
//		huyndai.drive();
//	}
//}

public class a4_User {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		a6_CarFactory carFactory = new a6_CarFactory();
		carFactory.viewCar("Honda");
		carFactory.viewCar("Toyota");
		carFactory.viewCar("Huyndai");

		carFactory.driverCar("Honda");
		carFactory.driverCar("Toyota");
		carFactory.driverCar("Huyndai");
	}
}
