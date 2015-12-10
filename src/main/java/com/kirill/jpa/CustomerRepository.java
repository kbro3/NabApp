package com.kirill.jpa;

import com.kirill.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}