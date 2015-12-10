package com.kirill;

import com.kirill.model.Customer;

public class CustomerBuilder {
  private Customer customer = new Customer();
  
  public CustomerBuilder id(int id) {
	    customer.setId(id);
	    return this;
	  }
  
  public CustomerBuilder firstName(String firstName) {
	    customer.setFirstName(firstName);
	    return this;
	  }
  
  public CustomerBuilder middleName(String middleName) {
	    customer.setMiddleName(middleName);
	    return this;
	  }
  
  public CustomerBuilder surname(String surname) {
	    customer.setSurname(surname);
	    return this;
	  }
 
  public CustomerBuilder initials(String initials) {
	    customer.setInitials(initials);
	    return this;
	  }
  
  public CustomerBuilder title(String title) {
	    customer.setTitle(title);
	    return this;
	  }
  
  public CustomerBuilder sex(String sex) {
	    customer.setSex(sex);
	    return this;
	  }
  
  public CustomerBuilder maritalStatus(String maritalStatus) {
	    customer.setMaritalStatus(maritalStatus);
	    return this;
	  }
  
  public CustomerBuilder creditRating(int creditRating) {
	    customer.setCreditRating(creditRating);
	    return this;
	  }
  
  public CustomerBuilder isCustomer(boolean isCustomer) {
	    customer.setCustomer(isCustomer);
	    return this;
	  }

  
  
  
  public Customer build() {
    return customer;
  }
}