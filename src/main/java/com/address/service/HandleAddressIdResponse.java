package com.address.service;

import com.address.model.Address;

public class HandleAddressIdResponse implements IHandleAddressResponse{

	@Override
	public Address getRelevantAddressOutput(Address addressOutput) {
		Address relevantAddressOutput = new Address();
		relevantAddressOutput.setId(addressOutput.getId());
		return relevantAddressOutput;
	}

}
