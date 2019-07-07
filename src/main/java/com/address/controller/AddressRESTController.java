package com.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.address.model.Address;
import com.address.model.AddressResponse;
import com.address.service.AddressService;

@Controller
public class AddressRESTController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/retrieveAddress/city/{city}/street/{street}/houseNumber/{houseNumber}")
	@ResponseBody
	public AddressResponse retrieveAddress(@PathVariable String city, @PathVariable String street,@PathVariable String houseNumber) {
		Address addressInput = new Address();
		addressInput.setCity(city);
		addressInput.setStreet(street);
		addressInput.setHouseNumber(houseNumber);
		return addressService.executeRetrieveAddress(addressInput);
	}

}
