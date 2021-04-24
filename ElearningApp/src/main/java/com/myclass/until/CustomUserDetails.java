package com.myclass.until;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String fristName;
	private String lastName;
	private String fullname ;
	
	public String getFullname() {
		return lastName  + " " + fristName;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public CustomUserDetails(String fullname, String password, Collection<? extends GrantedAuthority> authorities) {
		super(fullname, password, authorities);
		// TODO Auto-generated constructor stub
	}

}
