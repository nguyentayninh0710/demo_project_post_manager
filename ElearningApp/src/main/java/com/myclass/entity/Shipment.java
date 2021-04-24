package com.myclass.entity;
//Chuyến hàng

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="shipment")
public class Shipment {

	@Column(name = "SHIPMENT_ID")
	@Id
	private String shipmentId;
	
	@Column(name = "CARGO_CONTENT")
	private String cargoContent; //nội dung hàng hóa
	
	@Column(name = "TEQ_QUANTITY")
	private int teqQuantity; // định lượng
	
	@Column(name = "TOTAL_COST")
	private int totalCost; //Chi phí 
	
	@Column(name = "SHIP_REQUEST_DATE")
	private String shipRequestDate; // Ngày ship
	
	@Column(name = "NEED_BY_DATE")
	private String neddByDate; // ngày tới
	
	@Column(name = "STATUS")
	private String status; // trạng thái
	
	@Column(name = "INSURANCE_FLAG")
	private String insuranceFlag; //trạng thái bảo hiểm
	
	@Column(name = "INSURANCE_AMOUNT")
	private String insuranceAmount; // chi phí bảo hiểm
	
	@Column(name = "IS_DELETED")
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
	
	public Shipment() {
	}
	
	public Shipment(String shipmentId, String cargoContent, int teqQuantity, int totalCost, String shipRequestDate,
			String neddByDate, String status, String insuranceFlag, String insuranceAmount, int isDelete) {
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