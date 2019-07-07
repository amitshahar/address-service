package com.address.model;

public class AddressResponse {
	
	private Address addressOutput;
	private String errorMessage;

	public Address getAddress() {
		return addressOutput;
	}

	public void setAddress(Address addressOutput) {
		this.addressOutput = addressOutput;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
