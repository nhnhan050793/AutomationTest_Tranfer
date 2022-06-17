package A3_com.java.oop.accessModifier;

import A2_com.java.accessModifier.Class_01_TruyCapBenTrongClass;

//Class 03 là con của Class 01 dùng extends để kế thừa class 01
public class Class_03_TruyCapBenNgoaiPackageBoiClassCon extends Class_01_TruyCapBenTrongClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class_03_TruyCapBenNgoaiPackageBoiClassCon class003 = new Class_03_TruyCapBenNgoaiPackageBoiClassCon ();
		System.out.println(class003.getPhone());
	}
}
