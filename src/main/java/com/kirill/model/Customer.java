package com.kirill.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
@Table(name="Customer")
public class Customer{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Integer id;
	
	@ManyToOne
	private Address address;
	
	@Column(name="first_name")
	@NotNull
	@Size(min = 1, max = 30)
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name="surname")
	private String surname;
	
	@Column(name="initials")
	private String initials;
	
	@Column(name="title")
	private String title;
	
	//TODO: ADDRESS
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="marital_status")
	private String maritalStatus;
	
	@Column(name="credit_rating")
	private int creditRating;
	
	@Column(name="existing_customer")
	private boolean isCustomer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(int creditRating) {
		this.creditRating = creditRating;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
	

	
	
}