package A4_com.java.factory;

//public class a1_Honda {
//	public void drive() {
//		System.out.println("driving Honda");
//	}
//
//	public void view() {
//		System.out.println("viewing Honda car");
//	}
//}

public class a1_Honda extends a5_Car {

	@Override
	public void view() {
		// TODO Auto-generated method stub
		System.out.println("Viewing Honda car");			
	}

	@Override
	public void driver() {
		// TODO Auto-generated method stub
		System.out.println("Driving Honda car");	
	}
	
	@Override
	//option ( có hoặc ko mình thích thì @Override)
	public void carRunning() {
		System.out.println("Honda Car running!");
	}
	
}