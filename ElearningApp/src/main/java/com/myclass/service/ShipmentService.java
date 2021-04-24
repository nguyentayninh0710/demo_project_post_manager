package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.ShipmentDto;
import com.myclass.dto.UserDto;

@Service
public interface ShipmentService {
	List<ShipmentDto> getAll();
	ShipmentDto getById(String id);
	ServiceInfo add(ShipmentDto dto);
	boolean edit(String id, ShipmentDto dto);
	void delete(String id);
}