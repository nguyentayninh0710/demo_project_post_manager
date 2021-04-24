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

import com.myclass.dto.RouteDto;

@Entity
@Table(name = "route")
public class Route {
	@Column(name = "ROUTE_ID")
	@Id
	private String routeId;

	// Cảng đi
	@Column(name = "SOURCE_PORT_ID")
	private String sourcePortId;

	// Cảng đi
	@Column(name = "DEST_PORT_ID")
	private String destPortId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "IS_DELETED")
	private int isDelete;
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_PORT_ID",  updatable = false, insertable = false)
	private Port mtoSourcePort;
	
	@ManyToOne
	@JoinColumn(name = "SOURCE_PORT_ID",  updatable = false, insertable = false)
	private Port mtoDestPort;
	
	@OneToMany(mappedBy = "mtoRoute", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Voyage> voyage;

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
	public Route() {
		
	}
	public Route(String routeId, String sourcePortId, String destPortId, String name, int isDelete) {
		super();
		this.routeId = routeId;
		this.sourcePortId = sourcePortId;
		this.destPortId = destPortId;
		this.name = name;
		this.isDelete = isDelete;
	}
	
	public List<Voyage> getVoyage() {
		return voyage;
	}
	public void setVoyage(List<Voyage> voyage) {
		this.voyage = voyage;
	}


	
	
	
}
