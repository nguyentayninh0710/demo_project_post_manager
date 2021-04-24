package com.myclass.dto;

import javax.persistence.Column;

public class PortDto {
	private String portId;
	private String code;
	private String name;
	private String country;
	private int isDelete;
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public PortDto() {	
	}
	public PortDto(String portId, String code, String name, String country, int isDelete) {
		super();
		this.portId = portId;
		this.code = code;
		this.name = name;
		this.country = country;
		this.isDelete = isDelete;
	}
	
}
