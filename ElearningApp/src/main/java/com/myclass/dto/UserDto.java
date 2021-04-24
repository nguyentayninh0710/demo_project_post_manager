package com.myclass.dto;

import javax.persistence.Column;

public class UserDto {
	
	private String userId;
	private String loginName;
	private String fristName;
	private String lastName;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String role;
	private String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getFristName() {
		return fristName;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	private int isDelete;
	
	
	public UserDto() {
	}
	
	public UserDto(String userId, String loginName, String fristName, String lastName, String phone, String email,
			String address, String city, String state, String postalCode, String country, String role, String password,
			int isDelete) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.fristName = fristName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.role = role;
		this.password = password;
		this.isDelete = isDelete;
	}
	public UserDto(String userId, String loginName, String fristName, String lastName, String phone, String email,
			String address, String city, String state, String postalCode, String country, String role,
			int isDelete) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.fristName = fristName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.role = role;
		this.isDelete = isDelete;
	}

	
}
