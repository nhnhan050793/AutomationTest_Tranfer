package com.bankguru.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentJson {

	public static PaymentJson getJsonData(String filename) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(filename), PaymentJson.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@JsonProperty("fullName")
	private String fullName;
	

	
	@JsonProperty("email")
	private String emailAddress;
	
	@JsonProperty("streetAddress")
	private String streetAddress;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("phone")
	private String phoneNumber;


	public String getFullName() {
		return fullName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getCity() {
		return city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	@JsonProperty("paymentType")
	PaymentType paymentType;
	
	public static class PaymentType {
		@JsonProperty("visa")
		private String visaCard;
		
		@JsonProperty("master")
		private String MasterCard;
	}
	
	public String getVisaCard() {
		return paymentType.visaCard;
	}
	
	public String getMasterCard() {
		return paymentType.MasterCard;
	}
	
}
