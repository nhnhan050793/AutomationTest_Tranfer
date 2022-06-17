package A1_com.java.basic;

import java.util.Locale;

import com.github.javafaker.Faker;

public class Part_14_Faker {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale locale = new Locale("en");
		Faker faker = new Faker(locale);

		String name = faker.name().fullName(); // Miss Samanta Schmidt
		String firstName = faker.name().firstName(); // Emory
		String lastName = faker.name().lastName(); // Barton
		String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
		
		System.out.println(name);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(streetAddress);
		System.out.println(faker.phoneNumber().phoneNumber());
		System.out.println(faker.internet().domainName());
		System.out.println(faker.internet().emailAddress());
		System.out.println(faker.internet().ipV4Address());
		System.out.println(faker.internet().ipV6Address());
		System.out.println(faker.internet().ipV6Address());
		System.out.println(faker.internet().privateIpV4Address());
		System.out.println(faker.internet().publicIpV4Address());
		System.out.println(faker.internet().password(8, 16));
	}
}
