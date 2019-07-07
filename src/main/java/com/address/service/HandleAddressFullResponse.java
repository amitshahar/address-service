package com.address.service;

import com.address.model.Address;

public class HandleAddressFullResponse implements IHandleAddressResponse {

	@Override
	public Address getRelevantAddressOutput(Address addressOutput) {

		return addressOutput;
	}

}
