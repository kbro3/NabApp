package com.kirill;


import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.kirill.controller.CustomerController;
import com.kirill.jpa.CustomerRepository;
import com.kirill.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	private static final int CUSTOMER1_ID = 1;
	private static final Customer CUSTOMER1 = new CustomerBuilder()
			  .id(CUSTOMER1_ID)
			  .firstName("Alex")
			  .middleName("N")
			  .surname("Smith")
			  .initials("AS")
			  .title("Mr")
			  .maritalStatus("Married")
			  .creditRating(40)
			  .isCustomer(false)
			  .build();
	private static final Customer CUSTOMER2 = new CustomerBuilder()
			  .id(2)
			  .firstName("John")
			  .middleName("M")
			  .surname("Doe")
			  .initials("JD")
			  .title("Mr")
			  .maritalStatus("Single")
			  .creditRating(99)
			  .isCustomer(true)
			  .build();	
	
	private ArgumentCaptor<Customer> anyCustomer = ArgumentCaptor.forClass(Customer.class);
	
  @InjectMocks
  private CustomerController controller;
  @Mock
  private CustomerRepository repository;
  
  
  @Test
  public void whenFindingCustomersItShouldReturnAllCustomers() {
    // Given that the repository returns CUSTOMER1 and CUSTOMER2
    given(repository.findAll()).willReturn(Arrays.asList(CUSTOMER1, CUSTOMER2));
    // When looking for all customers, it should contain only CUSTOMER1 and CUSTOMER2
    assertThat(controller.findCustomers()).containsOnly(CUSTOMER1,CUSTOMER2);
  }
  
  @Test
  public void whenUpdatingCustomerItShouldReturnTheSavedCustomer() {
    // Given that CUSTOMER1 is returned when one is requested with CUSTOMER1_ID
    given(repository.getOne(CUSTOMER1_ID)).willReturn(CUSTOMER1);
    // Given that a CUSTOMER1 is saved and flushed, a CUSTOMER1 is returned
    given(repository.saveAndFlush(CUSTOMER1)).willReturn(CUSTOMER1);
    // When updating a CUSTOMER1
    assertThat(controller.updateCustomer(CUSTOMER1, CUSTOMER1_ID))
    // Then it should return the CUSTOMER1
    .isSameAs(CUSTOMER1);
  }
  
  @Test
  public void whenAddingCustomerItShouldReturnTheSavedCustomer() {
    // Given that a CUSTOMER1 is saved and flushed, a CUSTOMER2 is returned
    given(repository.saveAndFlush(CUSTOMER1)).willReturn(CUSTOMER2);
    // When adding a CUSTOMER1
    assertThat(controller.addCustomer(CUSTOMER1))
    // Then it should return the CUSTOMER2
    .isSameAs(CUSTOMER2);
  }
  
  @Test
  public void whenAddingCustomerItShouldMakeSureNoIDIsPassed() {
    // Given that a CUSTOMER1 is added
    controller.addCustomer(CUSTOMER1);
    // Verify that when the customer is saved
    verify(repository).saveAndFlush(anyCustomer.capture());
    // It should have an empty ID
    assertThat(anyCustomer.getValue().getId()).isNull();
  }
  
  @Test
  public void whenUpdatingCustomerItShouldUseTheGivenID() {
    // Given that CUSTOMER1 is returned when one is requested with CUSTOMER1_ID
    given(repository.getOne(CUSTOMER1_ID)).willReturn(CUSTOMER1);
    // Given that a CUSTOMER1 with CUSTOMER1_ID is updated
    controller.updateCustomer(CUSTOMER1, CUSTOMER1_ID);
    // Verify that when the customer is saved
    verify(repository).saveAndFlush(anyCustomer.capture());
    // It should have the given CUSTOMER1
    assertThat(anyCustomer.getValue().getId()).isEqualTo(CUSTOMER1_ID);
  }
  
  @Test
  public void whenDeletingCustomerItShouldUseTheRepository() {
    // Given that a customer with CUSTOMER1_ID is deleted
    controller.deleteCustomer(CUSTOMER1_ID);
    // Verify that the repository is used to delete the customer
    verify(repository).delete(CUSTOMER1_ID);
  }
  
}