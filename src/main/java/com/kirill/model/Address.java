package com.kirill.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
@Table(name="Address")
public class Address{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Integer id;
	
	@OneToMany
	private List<Customer> customers;
	
	@Column(name="street_number")
	private String streetNumber;
	
	@Column(name="street_name")
	private String streetName;
	
	@Column(name="suburb")
	private String suburb;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="pin_code")
	private String pinCode;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getStreetNumber() {
		return streetNumber;
	}



	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}



	public String getStreetName() {
		return streetName;
	}



	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}



	public String getSuburb() {
		return suburb;
	}



	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getPinCode() {
		return pinCode;
	}



	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	
	
}