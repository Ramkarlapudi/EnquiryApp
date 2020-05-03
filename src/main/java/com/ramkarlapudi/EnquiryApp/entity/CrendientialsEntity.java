package com.ramkarlapudi.EnquiryApp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Crendientials")

@NamedNativeQueries({

		@NamedNativeQuery(name = "getUserDataByName", query = "SELECT  * FROM Crendientials C  " + "WHERE C.User_name = ?", resultClass = CrendientialsEntity.class),
		@NamedNativeQuery(name = "getAll", query = "SELECT User_id, Password, Email_id, Phone, Address, State,City,Pincode "
				+ "FROM Crendientials", resultClass = CrendientialsEntity.class) })

public class CrendientialsEntity {
	@Id
	@GeneratedValue
	private int User_id;

	@Column(name = "User_name")
	private String User_name;

	@Column(name = "Password")
	private String Password;

	@Column(name = "Email_id")
	private String Email_id;

	@Column(name = "Phone")
	private Long Phone;

	@Column(name = "Address")
	private String Address;

	@Column(name = "State")
	private String State;

	@Column(name = "City")
	private String City;

	@Column(name = "Pincode")
	private int Pincode;

	@OneToOne(mappedBy = "crendientialsEntity" ,cascade = CascadeType.ALL)
	private UserActivityEntity userActivityEntity;
	
	public int getUser_id() {
		return User_id;
	}

	protected UserActivityEntity getUserActivityEntity() {
		return userActivityEntity;
	}

	protected void setUserActivityEntity(UserActivityEntity userActivityEntity) {
		this.userActivityEntity = userActivityEntity;
	}

	public void setUser_id(int user_id) {
		User_id = user_id;
	}

	public String getUser_name() {
		return User_name;
	}

	public void setUser_name(String user_name) {
		User_name = user_name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail_id() {
		return Email_id;
	}

	public void setEmail_id(String email_id) {
		Email_id = email_id;
	}

	public Long getPhone() {
		return Phone;
	}

	public void setPhone(Long phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public int getPincode() {
		return Pincode;
	}

	public void setPincode(int pincode) {
		Pincode = pincode;
	}

}
