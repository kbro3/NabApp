package com.kirill;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;


import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.kirill.jpa.CustomerRepository;
import com.kirill.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NabAppApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CustomerControllerIT {   
	
	private static final String FIRSTNAME_FIELD = "firstName";
	private static final String CUSTOMERS_RESOURCE = "/customers";
	private static final String CUSTOMER_RESOURCE = "/customers/{id}";
	private static final int NON_EXISTING_ID = 999;
	
	private static final int CUSTOMER1_ID = 1;
	private static final String FIRST_CUSTOMER_FIRSTNAME = "Alex";
	private static final String FIRST_CUSTOMER_MIDDLENAME = "N";
	private static final String FIRST_CUSTOMER_SURNAME = "Smith";
	private static final String FIRST_CUSTOMER_INITIALS = "AS";
	private static final String FIRST_CUSTOMER_TITLE = "Mr";
	private static final String FIRST_CUSTOMER_SEX = "Male";
	private static final String FIRST_CUSTOMER_MARITALSTATUS = "Married";
	private static final int FIRST_CUSTOMER_CREDITRATING = 50;
	private static final boolean FIRST_CUSTOMER_ISCUSTOMER = false;
	
	private static final int CUSTOMER2_ID = 2;
	private static final String SECOND_CUSTOMER_FIRSTNAME = "John";
	private static final String SECOND_CUSTOMER_MIDDLENAME = "M";
	private static final String SECOND_CUSTOMER_SURNAME = "Doe";
	private static final String SECOND_CUSTOMER_INITIALS = "JD";
	private static final String SECOND_CUSTOMER_TITLE = "Mr";
	private static final String SECOND_CUSTOMER_SEX = "Male";
	private static final String SECOND_CUSTOMER_MARITALSTATUS = "Single";
	private static final int SECOND_CUSTOMER_CREDITRATING = 70;
	private static final boolean SECOND_CUSTOMER_ISCUSTOMER = false;
	
	private static final int CUSTOMER3_ID = 3;
	private static final String THIRD_CUSTOMER_FIRSTNAME = "Lara";
	private static final String THIRD_CUSTOMER_MIDDLENAME = "B";
	private static final String THIRD_CUSTOMER_SURNAME = "Brown";
	private static final String THIRD_CUSTOMER_INITIALS = "LB";
	private static final String THIRD_CUSTOMER_TITLE = "Mrs";
	private static final String THIRD_CUSTOMER_SEX = "Female";
	private static final String THIRD_CUSTOMER_MARITALSTATUS = "Married";
	private static final int THIRD_CUSTOMER_CREDITRATING = 99;
	private static final boolean THIRD_CUSTOMER_ISCUSTOMER = false;
	
	private static final Customer CUSTOMER1 = new CustomerBuilder()
			  .id(CUSTOMER1_ID)
			  .firstName(FIRST_CUSTOMER_FIRSTNAME)
			  .middleName(FIRST_CUSTOMER_MIDDLENAME)
			  .surname(FIRST_CUSTOMER_SURNAME)
			  .initials(FIRST_CUSTOMER_INITIALS)
			  .title(FIRST_CUSTOMER_TITLE)
			  .sex(FIRST_CUSTOMER_SEX)
			  .maritalStatus(FIRST_CUSTOMER_MARITALSTATUS)
			  .creditRating(FIRST_CUSTOMER_CREDITRATING)
			  .isCustomer(FIRST_CUSTOMER_ISCUSTOMER)
			  .build();
	private static final Customer CUSTOMER2 = new CustomerBuilder()
			  .id(CUSTOMER2_ID)
			  .firstName(SECOND_CUSTOMER_FIRSTNAME)
			  .middleName(SECOND_CUSTOMER_MIDDLENAME)
			  .surname(SECOND_CUSTOMER_SURNAME)
			  .initials(SECOND_CUSTOMER_INITIALS)
			  .title(SECOND_CUSTOMER_TITLE)
			  .sex(SECOND_CUSTOMER_SEX)
			  .maritalStatus(SECOND_CUSTOMER_MARITALSTATUS)
			  .creditRating(SECOND_CUSTOMER_CREDITRATING)
			  .isCustomer(SECOND_CUSTOMER_ISCUSTOMER)
			  .build();
	private static final Customer CUSTOMER3 = new CustomerBuilder()
			  .id(CUSTOMER3_ID)
			  .firstName(THIRD_CUSTOMER_FIRSTNAME)
			  .middleName(THIRD_CUSTOMER_MIDDLENAME)
			  .surname(THIRD_CUSTOMER_SURNAME)
			  .initials(THIRD_CUSTOMER_INITIALS)
			  .title(THIRD_CUSTOMER_TITLE)
			  .sex(THIRD_CUSTOMER_SEX)
			  .maritalStatus(THIRD_CUSTOMER_MARITALSTATUS)
			  .creditRating(THIRD_CUSTOMER_CREDITRATING)
			  .isCustomer(THIRD_CUSTOMER_ISCUSTOMER)
			  .build();
  
  
  @Autowired
  private CustomerRepository repository;
  
  @Value("${local.server.port}")
  private int serverPort;
  private Customer firstCustomer;
  private Customer secondCustomer;
  
  @Before
  public void setUp() {
    repository.deleteAll();
    firstCustomer = repository.save(CUSTOMER1);
    secondCustomer = repository.save(CUSTOMER2);
    RestAssured.port = serverPort;
  }
  
  
  @Test
  public void getCustomersShouldReturnBothCustomers() {
    when()
      .get(CUSTOMERS_RESOURCE)
    .then()
      .statusCode(HttpStatus.SC_OK)
      .body(FIRSTNAME_FIELD, hasItems(FIRST_CUSTOMER_FIRSTNAME, SECOND_CUSTOMER_FIRSTNAME));
      
  }
  
  @Test
  public void addCustomerShouldReturnSavedCustomer() {
    given()
      .body(CUSTOMER3)
      .contentType(ContentType.JSON)
    .when()
      .post(CUSTOMERS_RESOURCE)
    .then()
      .statusCode(HttpStatus.SC_OK)
      .body(FIRSTNAME_FIELD, is(THIRD_CUSTOMER_FIRSTNAME));
      
  }
  
  @Test
  public void addCustomerShouldReturnBadRequestWithoutBody() {
    when()
      .post(CUSTOMERS_RESOURCE)
    .then()
      .statusCode(HttpStatus.SC_BAD_REQUEST);
  }
  
  @Test
  public void addCustomerShouldReturnNotSupportedMediaTypeIfNonJSON() {
    given()
      .body(CUSTOMER3)
    .when()
      .post(CUSTOMERS_RESOURCE)
    .then()
      .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
  }
  
  @Test
  public void updateCustomerShouldReturnUpdatedCustomer() {
    
    Customer customer = new CustomerBuilder()
    		.id(CUSTOMER1_ID)
			  .firstName(FIRST_CUSTOMER_FIRSTNAME)
			  .middleName("N")
			  .surname("Smith")
			  .initials("AS")
			  .title("Mr")
			  .sex("hehe")
			  .maritalStatus("Married")
			  .creditRating(40)
			  .isCustomer(false)
			  .build();
    given()
      .body(customer)
      .contentType(ContentType.JSON)
    .when()
      .put(CUSTOMER_RESOURCE, firstCustomer.getId())
    .then()
      .statusCode(HttpStatus.SC_OK)
      .body(FIRSTNAME_FIELD, is(FIRST_CUSTOMER_FIRSTNAME));
      
  }
  
  @Test
  public void updateCustomerShouldReturnBadRequestWithoutBody() {
    when()
      .put(CUSTOMERS_RESOURCE, firstCustomer.getId())
    .then()
      .statusCode(HttpStatus.SC_BAD_REQUEST);
  }
  
  @Test
  public void updateCustomerShouldReturnNotSupportedMediaTypeIfNonJSON() {
    given()
      .body(CUSTOMER1)
    .when()
      .put(CUSTOMERS_RESOURCE, firstCustomer.getId())
    .then()
      .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
  }
  
  @Test
  public void updateCustomerShouldBeBadRequestIfNonExistingID() {
    given()
      .body(CUSTOMER1)
      .contentType(ContentType.JSON)
    .when()
      .put(CUSTOMER_RESOURCE, NON_EXISTING_ID)
    .then()
      .statusCode(HttpStatus.SC_BAD_REQUEST);
  }
  
  @Test
  public void deleteCustomerShouldReturnNoContent() {
    when()
      .delete(CUSTOMERS_RESOURCE, secondCustomer.getId())
    .then()
      .statusCode(HttpStatus.SC_NO_CONTENT);
  }
  
  @Test
  public void deleteCustomerShouldBeBadRequestIfNonExistingID() {
    when()
      .delete(CUSTOMER_RESOURCE, NON_EXISTING_ID)
    .then()
      .statusCode(HttpStatus.SC_BAD_REQUEST);
  }
  
  
  
  
  
  
  
  
}