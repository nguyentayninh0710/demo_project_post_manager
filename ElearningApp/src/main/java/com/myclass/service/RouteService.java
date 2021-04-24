package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.RouteDto;

@Service
public interface RouteService {

	List<RouteDto> getAll();

	RouteDto getById(String routeId);

	ServiceInfo add(RouteDto dtoRoute);

	boolean edit(String routeId, RouteDto dtoRoute);

	void delete(String routeId);

}
