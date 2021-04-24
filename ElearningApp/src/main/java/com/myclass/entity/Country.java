package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="country")
public class Country {	
	@Column(name = "ID")
	@Id
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "IS_DELETED")
	private int isDelete;
	

	@OneToMany(mappedBy = "mtoCountry", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	public Country() {		
	}
	
	public Country(String id, String name, int isDelete) {
		super();
		this.id = id;
		this.name = name;
		this.isDelete = isDelete;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
	
}
