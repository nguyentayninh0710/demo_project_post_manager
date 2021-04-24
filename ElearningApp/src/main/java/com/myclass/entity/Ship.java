package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name = "ship")
public class Ship {
	@Column(name = "SHIP_ID")
	@Id
	private String shipId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CAPACITY")
	private int capacity; // Sức chứa

	@Column(name = "IS_DELETED")
	private int isDelete;
	
	@OneToMany(mappedBy = "mtoRoute", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Voyage> voyage;
	
	public List<Voyage> getVoyage() {
		return voyage;
	}
	public void setVoyage(List<Voyage> voyage) {
		this.voyage = voyage;
	}
	public String getShipId() {
		return shipId;
	}
	public void setShipId(String shipId) {
		this.shipId = shipId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Ship() {
		
	}
	public Ship(String shipId, String name, int capacity, int isDelete) {
		super();
		this.shipId = shipId;
		this.name = name;
		this.capacity = capacity;
		this.isDelete = isDelete;
	}
	
	
}
