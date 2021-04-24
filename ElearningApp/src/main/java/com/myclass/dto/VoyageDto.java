package com.myclass.dto;

public class VoyageDto {

	private String voyageId;
	private String shipId;
	private String routeId;
	private String startDate;
	private String endDate;
	private String voyageQuantity;
	private String costPreTeq;
	private int isDelete;

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

	public VoyageDto() {
		super();
	}

	public VoyageDto(String shipId, String routeId, String startDate, String endDate, String voyageQuantity,
			String costPreTeq, int isDelete) {
		super();
		this.shipId = shipId;
		this.routeId = routeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.voyageQuantity = voyageQuantity;
		this.costPreTeq = costPreTeq;
		this.isDelete = isDelete;
	}

	public VoyageDto(String voyageId, String shipId, String routeId, String startDate, String endDate,
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
