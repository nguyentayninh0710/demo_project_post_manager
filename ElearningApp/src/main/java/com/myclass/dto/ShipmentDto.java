package com.myclass.dto;

public class ShipmentDto {
	
	private String shipmentId;
	private String cargoContent; //nội dung hàng hóa
	private int teqQuantity; // định lượng
	private int totalCost; //Chi phí 
	private String shipRequestDate; // Ngày ship
	private String neddByDate; // ngày tới
	private String status; // trạng thái
	private String insuranceFlag; //trạng thái bảo hiểm
	private String insuranceAmount; // chi phí bảo hiểm
	private int isDelete;
	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getCargoContent() {
		return cargoContent;
	}
	public void setCargoContent(String cargoContent) {
		this.cargoContent = cargoContent;
	}
	public int getTeqQuantity() {
		return teqQuantity;
	}
	public void setTeqQuantity(int teqQuantity) {
		this.teqQuantity = teqQuantity;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	public String getShipRequestDate() {
		return shipRequestDate;
	}
	public void setShipRequestDate(String shipRequestDate) {
		this.shipRequestDate = shipRequestDate;
	}
	public String getNeddByDate() {
		return neddByDate;
	}
	public void setNeddByDate(String neddByDate) {
		this.neddByDate = neddByDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInsuranceFlag() {
		return insuranceFlag;
	}
	public void setInsuranceFlag(String insuranceFlag) {
		this.insuranceFlag = insuranceFlag;
	}
	public String getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public ShipmentDto() {}
	public ShipmentDto(String shipmentId, 
			String cargoContent, int teqQuantity, 
			int totalCost, String shipRequestDate,
			String neddByDate, String status, 
			String insuranceFlag, 
			String insuranceAmount, int isDelete) {
		super();
		this.shipmentId = shipmentId;
		this.cargoContent = cargoContent;
		this.teqQuantity = teqQuantity;
		this.totalCost = totalCost;
		this.shipRequestDate = shipRequestDate;
		this.neddByDate = neddByDate;
		this.status = status;
		this.insuranceFlag = insuranceFlag;
		this.insuranceAmount = insuranceAmount;
		this.isDelete = isDelete;
	}
	
	
	
}
