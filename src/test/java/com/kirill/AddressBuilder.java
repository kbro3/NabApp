package com.kirill;

import com.kirill.model.Address;

public class AddressBuilder {
  private Address address = new Address();
  
  public AddressBuilder id(int id) {
	    address.setId(id);
	    return this;
	  }
  
  public AddressBuilder streetNumber(String streetNumber) {
	    address.setStreetNumber(streetNumber);
	    return this;
	  }
  
  public AddressBuilder streetName(String streetName) {
	    address.setStreetName(streetName);
	    return this;
	  }
  
  public AddressBuilder suburb(String suburb) {
	    address.setSuburb(suburb);
	    return this;
	  }
 
  public AddressBuilder city(String city) {
	    address.setCity(city);
	    return this;
	  }
  
  public AddressBuilder state(String state) {
	    address.setState(state);
	    return this;
	  }
  
  public AddressBuilder country(String country) {
	    address.setCountry(country);
	    return this;
	  }
  
  public AddressBuilder pinCode(String pinCode) {
	    address.setPinCode(pinCode);
	    return this;
	  }

  
  public Address build() {
    return address;
  }
}