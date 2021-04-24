package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="port")
public class Port {
	
	@Column(name = "PORT_ID")
	@Id
	private String portId;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "IS_DELETED")
	private int isDelete;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY",  updatable = false, insertable = false)
	private Country mtoCountry;
	
	@OneToMany(mappedBy = "mtoSourcePort", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Route> route1;
	
	@OneToMany(mappedBy = "mtoDestPort", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Route> route2;
	
	public Port() {
		
	}
	public Port(String portId, String code, String name, String country, int isDelete) {
		super();
		this.portId = portId;
		this.code = code;
		this.name = name;
		this.country = country;
		this.isDelete = isDelete;
	}
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
	public List<Route> getRoute1() {
		return route1;
	}
	public void setRoute1(List<Route> route1) {
		this.route1 = route1;
	}
	public List<Route> getRoute2() {
		return route2;
	}
	public void setRoute2(List<Route> route2) {
		this.route1 = route2;
	}
	
}
