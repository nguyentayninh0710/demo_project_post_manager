package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name = "voyage")
public class Voyage {
	@Column(name = "VOYAGE_ID")
	@Id
	private String voyageId;

	@Column(name = "SHIP_ID")
	private String shipId;

	@Column(name = "ROUTE_ID")
	private String routeId;

	@Column(name = "START_DATE")
	private String startDate;

	@Column(name = "END_DATE")
	private String endDate;

	@Column(name = "VOYAGE_QUANTITY")
	private String voyageQuantity; // quãng đường

	@Column(name = "COST_PRE_TEQ")
	private String costPreTeq;

	@Column(name = "IS_DELETE")
	private int isDelete;
	
	@ManyToOne
	@JoinColumn(name = "SHIP_ID",  updatable = false, insertable = false)
	private Ship mtoShip;
	
	@ManyToOne
	@JoinColumn(name = "ROUTE_ID",  updatable = false, insertable = false)
	private Route mtoRoute;

	public String getVoyageId() {
		return voyageId;
	}

	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
	}

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getVoyageQuantity() {
		return voyageQuantity;
	}

	public void setVoyageQuantity(String voyageQuantity) {
		this.voyageQuantity = voyageQuantity;
	}

	public String getCostPreTeq() {
		return costPreTeq;
	}

	public void setCostPreTeq(String costPreTeq) {
		this.costPreTeq = costPreTeq;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Voyage() {
		
	}
	public Voyage(String voyageId, String shipId, String routeId, String startDate, String endDate,
			String voyageQuantity, String costPreTeq, int isDelete) {
		super();
		this.voyageId = voyageId;
		this.shipId = shipId;
		this.routeId = routeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.voyageQuantity = voyageQuantity;
		this.costPreTeq = costPreTeq;
		this.isDelete = isDelete;
	}
	
	
}
