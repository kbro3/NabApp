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

import com.kirill.controller.AddressController;
import com.kirill.controller.CustomerController;
import com.kirill.jpa.AddressRepository;
import com.kirill.jpa.CustomerRepository;
import com.kirill.model.Address;
import com.kirill.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

	private static final int ADDRESS1_ID = 1;
	private static final Address ADDRESS1 = new AddressBuilder()
			  .id(ADDRESS1_ID)
			  .streetNumber("5")
			  .streetName("Green crt")
			  .suburb("Cheltenham")
			  .city("Melbourne")
			  .state("VIC")
			  .country("Australia")
			  .pinCode("3197")
			  .build();
	private static final Address ADDRESS2 = new AddressBuilder()
			  .id(2)
			  .streetNumber("7")
			  .streetName("Brown street")
			  .suburb("Edithvale")
			  .city("Melbourne")
			  .state("VIC")
			  .country("Australia")
			  .pinCode("3192")
			  .build();	
	
	private ArgumentCaptor<Address> anyAddress = ArgumentCaptor.forClass(Address.class);
	
  @InjectMocks
  private AddressController controller;
  @Mock
  private AddressRepository repository;
  
  
  @Test
  public void whenFindingAddressItShouldReturnOneAddress() {
    // Given that the repository returns ADDRESS1 and ADDRESS2
    given(repository.findOne(ADDRESS1_ID)).willReturn(ADDRESS1);
    // When looking for all customers, it should contain only ADDRESS1 and ADDRESS2
    assertThat(controller.getAddress(ADDRESS1_ID)).isSameAs(ADDRESS1);
  }
  
  @Test
  public void whenFindingAddressesItShouldReturnAllAddresses() {
    // Given that the repository returns ADDRESS1 and ADDRESS2
    given(repository.findAll()).willReturn(Arrays.asList(ADDRESS1, ADDRESS2));
    // When looking for all customers, it should contain only ADDRESS1 and ADDRESS2
    assertThat(controller.findAddresses()).containsOnly(ADDRESS1,ADDRESS2);
  }
  
  @Test
  public void whenUpdatingCustomerItShouldReturnTheSavedCustomer() {
    // Given that ADDRESS1 is returned when one is requested with ADDRESS1_ID
    given(repository.getOne(ADDRESS1_ID)).willReturn(ADDRESS1);
    // Given that a ADDRESS1 is saved and flushed, a ADDRESS1 is returned
    given(repository.saveAndFlush(ADDRESS1)).willReturn(ADDRESS1);
    // When updating a ADDRESS1
    assertThat(controller.updateAddress(ADDRESS1, ADDRESS1_ID))
    // Then it should return the ADDRESS1
    .isSameAs(ADDRESS1);
  }
  
  @Test
  public void whenAddingCustomerItShouldReturnTheSavedCustomer() {
    // Given that a ADDRESS1 is saved and flushed, a ADDRESS2 is returned
    given(repository.saveAndFlush(ADDRESS1)).willReturn(ADDRESS2);
    // When adding a ADDRESS1
    assertThat(controller.addAddress(ADDRESS1))
    // Then it should return the ADDRESS2
    .isSameAs(ADDRESS2);
  }
  
  @Test
  public void whenAddingCustomerItShouldMakeSureNoIDIsPassed() {
    // Given that a ADDRESS1 is added
    controller.addAddress(ADDRESS1);
    // Verify that when the customer is saved
    verify(repository).saveAndFlush(anyAddress.capture());
    // It should have an empty ID
    assertThat(anyAddress.getValue().getId()).isNull();
  }
  
  @Test
  public void whenUpdatingCustomerItShouldUseTheGivenID() {
    // Given that ADDRESS1 is returned when one is requested with ADDRESS1_ID
    given(repository.getOne(ADDRESS1_ID)).willReturn(ADDRESS1);
    // Given that a ADDRESS1 with ADDRESS1_ID is updated
    controller.updateAddress(ADDRESS1, ADDRESS1_ID);
    // Verify that when the customer is saved
    verify(repository).saveAndFlush(anyAddress.capture());
    // It should have the given ADDRESS1
    assertThat(anyAddress.getValue().getId()).isEqualTo(ADDRESS1_ID);
  }
  
  @Test
  public void whenDeletingCustomerItShouldUseTheRepository() {
    // Given that a customer with ADDRESS1_ID is deleted
    controller.deleteAddress(ADDRESS1_ID);
    // Verify that the repository is used to delete the customer
    verify(repository).delete(ADDRESS1_ID);
  }
  
}