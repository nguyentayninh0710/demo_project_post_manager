package com.myclass.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.myclass.entity.Port;

public class RouteDto {
	private String routeId;
	private String sourcePortId;
	private String destPortId;
	private String name;
	private int isDelete;


	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getSourcePortId() {
		return sourcePortId;
	}

	public void setSourcePortId(String sourcePortId) {
		this.sourcePortId = sourcePortId;
	}

	public String getDestPortId() {
		return destPortId;
	}

	public void setDestPortId(String destPortId) {
		this.destPortId = destPortId;
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
	public RouteDto() {
		
	}
	public RouteDto(String routeId, String sourcePortId, String destPortId, String name, int isDelete) {
		super();
		this.routeId = routeId;
		this.sourcePortId = sourcePortId;
		this.destPortId = destPortId;
		this.name = name;
		this.isDelete = isDelete;
	}
}
