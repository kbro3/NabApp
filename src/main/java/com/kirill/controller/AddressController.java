package com.kirill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kirill.jpa.AddressRepository;
import com.kirill.model.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	
	
	@Autowired
	private AddressRepository addrepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List findAddresses(){
		return addrepo.findAll();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Address getAddress(@PathVariable Integer id){
		return addrepo.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Address addAddress(@RequestBody Address address) {
		address.setId(null);
		return addrepo.saveAndFlush(address);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Address updateAddress(@RequestBody Address updatedAddress, @PathVariable Integer id) {
		updatedAddress.setId(id);		 
		return addrepo.saveAndFlush(updatedAddress);
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteAddress(@PathVariable Integer id) {
		addrepo.delete(id);
	}
	
	
	
}