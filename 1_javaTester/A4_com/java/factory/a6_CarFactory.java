package A4_com.java.factory;

public class a6_CarFactory {
	public void viewCar(String carName) {
		if (carName.equals("Honda")) {
			a1_Honda honda = new a1_Honda();
			honda.view();
			honda.carRunning();
		} else if (carName.equals("Toyota")) {
			a3_Toyota toyota = new a3_Toyota();
			toyota.view();
			toyota.carRunning();
		} else if (carName.equals("Huyndai")) {
			a2_Huyndai huyndai = new a2_Huyndai();
			huyndai.view();
			huyndai.carRunning();
		}
	}

	public void driverCar(String carName) {
		if (carName.equals("Honda")) {
			a1_Honda honda = new a1_Honda();
			honda.driver();
		} else if (carName.equals("Toyota")) {
			a3_Toyota toyota = new a3_Toyota();
			toyota.driver();
		} else if (carName.equals("Huyndai")) {
			a2_Huyndai huyndai = new a2_Huyndai();
			huyndai.driver();
		}
	}
}
