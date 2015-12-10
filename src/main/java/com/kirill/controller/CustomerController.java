package com.kirill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kirill.jpa.AddressRepository;
import com.kirill.jpa.CustomerRepository;
import com.kirill.model.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private AddressRepository addrepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List findCustomers(){
		return repo.findAll();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable Integer id){
		return repo.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Customer addCustomer(@RequestBody Customer customer) {
		customer.setId(null);
		return repo.saveAndFlush(customer);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable Integer id) {
		updatedCustomer.setId(id);		 
		return repo.saveAndFlush(updatedCustomer);
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable Integer id) {
		repo.delete(id);
	}
	
	
	
}