package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.ShipDto;

@Service
public interface ShipService {

	List<ShipDto> getAll();

	ShipDto getById(String shipId);

	ServiceInfo add(ShipDto dtoShip);

	boolean edit(String shipId, ShipDto dtoShip);

	void delete(String shipId);

}
