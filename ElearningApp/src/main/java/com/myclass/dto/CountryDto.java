package com.myclass.dto;

public class CountryDto {
	private String id;
	private String name;
	private int isDelete;
	
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
	public CountryDto() {
		
	}
	public CountryDto(String id, String name, int isDelete) {
		super();
		this.id = id;
		this.name = name;
		this.isDelete = isDelete;
	}
	
	
}
