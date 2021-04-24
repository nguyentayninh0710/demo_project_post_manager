package com.myclass.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.ShipmentDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Shipment;
import com.myclass.repository.ShipmentRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService{

	@Autowired
	private ShipmentRepository shipmentRepository;

	
	@Override
	public List<ShipmentDto> getAll() {
		List<ShipmentDto> dtos = new ArrayList<ShipmentDto>();
		List<Shipment> shipments = shipmentRepository.findAll();
		// Ánh xạ dữ liệu từ Entity qua DTO
		for (Shipment shipment : shipments) {
			dtos.add(new ShipmentDto
					(shipment.getShipmentId(),
					  shipment.getCargoContent(),
					  shipment.getTeqQuantity(),
					  shipment.getTotalCost(),
					  shipment.getShipRequestDate(),
					  shipment.getNeddByDate(),
					  shipment.getStatus(),
					  shipment.getInsuranceFlag(),
					  shipment.getInsuranceAmount(),
					  shipment.getIsDelete()));
		}
		return dtos;
	}

	@Override
	public ShipmentDto getById(String id) {
		Shipment shipment = shipmentRepository.findByShipmentId(id).get();
		return entityToDto(shipment);
	}
	private ShipmentDto entityToDto(Shipment entity) {
		return new ShipmentDto(entity.getShipmentId(),
				entity.getCargoContent(),
				entity.getTeqQuantity(),
				entity.getTotalCost(),
				entity.getShipRequestDate(),
				entity.getNeddByDate(),
				entity.getStatus(),
				entity.getInsuranceFlag(),
				entity.getInsuranceAmount(),
				  0);
				
			
	}
	@Override
	public ServiceInfo add(ShipmentDto dto) {
		int count = shipmentRepository.countByShipmentId(dto.getShipmentId());
		if (count > 0) {
			return new ServiceInfo(false, "Hành trình đã có đã sử dụng!");
		}
		try {
			// nếu chưa thì thêm mới
			Shipment shipment = new Shipment();
			shipment.setShipmentId(dto.getShipmentId()); 
			shipment.setCargoContent(dto.getCargoContent()); 
			shipment.setTeqQuantity(dto.getTeqQuantity()); 
			shipment.setTotalCost(dto.getTotalCost()); 
			shipment.setShipRequestDate(dto.getShipRequestDate()); 
			shipment.setNeddByDate(dto.getNeddByDate()); 
			shipment.setStatus(dto.getStatus()); 
			shipment.setInsuranceFlag(dto.getInsuranceFlag()); 
			shipment.setInsuranceAmount(dto.getInsuranceAmount()); 
			shipment.setIsDelete(0);
			shipmentRepository.save(shipment);
			return new ServiceInfo(true, "Thêm mới thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(String id, ShipmentDto dto) {
		Optional<Shipment> optional = shipmentRepository.findByShipmentId(id);
		if (optional.isPresent()== false) return false;
		Shipment shipment = optional.get();
		shipment.setShipmentId(dto.getShipmentId()); 
		shipment.setCargoContent(dto.getCargoContent()); 
		shipment.setTeqQuantity(dto.getTeqQuantity()); 
		shipment.setTotalCost(dto.getTotalCost()); 
		shipment.setShipRequestDate(dto.getShipRequestDate()); 
		shipment.setNeddByDate(dto.getNeddByDate()); 
		shipment.setStatus(dto.getStatus()); 
		shipment.setInsuranceFlag(dto.getInsuranceFlag()); 
		shipment.setInsuranceAmount(dto.getInsuranceAmount()); 
		shipment.setIsDelete(0);
		shipmentRepository.save(shipment);
		
		return true;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		shipmentRepository.deleteByShipmentId(id);
		
	}

}