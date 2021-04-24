package com.myclass.dto;

public class ShipDto {

	private String shipId;
	private String name;
	private int capacity;
	private int isDelete;

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

	public ShipDto(String shipId, String name, int capacity, int isDelete) {
		super();
		this.shipId = shipId;
		this.name = name;
		this.capacity = capacity;
		this.isDelete = isDelete;
	}

	public ShipDto(String name, int capacity, int isDelete) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.isDelete = isDelete;
	}

	public ShipDto() {
		super();
	}

}
