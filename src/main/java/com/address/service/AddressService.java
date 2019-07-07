package com.address.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.address.data.ExistingAddresses;
import com.address.model.Address;
import com.address.model.AddressResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AddressService {
	
	@Value("${service.address.handleAddressResponseFlag}")
	private String handleAddressResponseFlag;
	
	private ExistingAddresses existingAddresses = null;
	private static Map<String, IHandleAddressResponse> interfaceToConcreteClassMap = new HashMap<String, IHandleAddressResponse>();

	static
	{
		interfaceToConcreteClassMap.put("ID", new HandleAddressIdResponse());
		interfaceToConcreteClassMap.put("FULL", new HandleAddressFullResponse());
	}

	public AddressResponse executeRetrieveAddress(Address addressInput) 
	{
		AddressResponse addressResponse = new AddressResponse();
		Address[] allAddresses = getExistingAddresses();	
		Address addressOutput = getAddressIfExist(allAddresses, addressInput);
		if(addressOutput == null)
		{
			addressResponse.setErrorMessage("Input Address not exist on DB!");
		}
		else
		{
			IHandleAddressResponse handleAddressResponse = interfaceToConcreteClassMap.get(handleAddressResponseFlag);
			addressResponse.setAddress(handleAddressResponse.getRelevantAddressOutput(addressOutput));
		}	
		return addressResponse;
	}

	private Address getAddressIfExist(Address[] allAddresses, Address addressInput) 
	{
		for (Address existAddress : allAddresses) 
		{
			if(isSameAddress(existAddress, addressInput))
			{
				return existAddress;
			}
		}
		System.out.println("AddressService.getAddressIfExist - "
				+ "Input address is not exist on DB therefore returning null value.");
		return null;
	}

	private boolean isSameAddress(Address existAddress, Address addressInput) 
	{
		if(existAddress.getCity().equalsIgnoreCase(addressInput.getCity())
				&& existAddress.getStreet().equalsIgnoreCase(addressInput.getStreet())
				&& existAddress.getHouseNumber().equals(addressInput.getHouseNumber()))
		{
			return true;
		}
		
		return false;
	}

	private Address[] getExistingAddresses() 
	{
		if(existingAddresses==null)
		{
			loadExistingAddresses(); 
		}
		return existingAddresses.getAllAddresses();
	}

	private void loadExistingAddresses() 
	{
        ObjectMapper objectMapper = new ObjectMapper();

        try 
        {
        	File file = ResourceUtils.getFile("classpath:existingAddresses.json");
			existingAddresses = objectMapper.readValue(file, ExistingAddresses.class);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}	
	}

}
