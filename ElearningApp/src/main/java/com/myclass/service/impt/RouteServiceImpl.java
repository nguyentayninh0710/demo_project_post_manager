package com.myclass.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.RouteDto;
import com.myclass.entity.Route;
import com.myclass.repository.RouteRepository;
import com.myclass.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {
	@Autowired
	private RouteRepository routeRepository;

	public List<RouteDto> getAll() {
		List<RouteDto> dtos = new ArrayList<RouteDto>();
		// Gọi phương thức của Repository để truy vấn dữ liệu
		List<Route> routes = routeRepository.findAll();
		// Ánh xạ dữ liệu từ Entity qua DTO
		for (Route route : routes) {
			dtos.add(new RouteDto(route.getRouteId(), route.getSourcePortId(), route.getDestPortId(),route.getName(), route.getIsDelete()));
		}
		return dtos;
	}

	@Override
	public ServiceInfo add(RouteDto dto) {
		int count = routeRepository.countByName(dto.getName());
		if (count > 0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			// nếu chưa thì thêm mới
			Route route = new Route();
			route.setRouteId(dto.getRouteId());
			route.setSourcePortId(dto.getSourcePortId());
			route.setDestPortId(dto.getDestPortId());
			route.setName(dto.getName());
			route.setIsDelete(0);
			routeRepository.save(route);
			return new ServiceInfo(true, "Thêm mới thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public RouteDto getById(String id) {
		Route route = routeRepository.findByRouteId(id).get();
		return entityToDto(route);
	}

	private RouteDto entityToDto(Route route) {
		return new RouteDto(route.getRouteId(), route.getSourcePortId(), route.getDestPortId(),route.getName(), 0);
	}

	@Override
	public boolean edit(String id, RouteDto dto) {
		Optional<Route> optional = routeRepository.findByRouteId(id);
		if (optional.isPresent() == false)
			return false;
		Route route = optional.get();
		route.setRouteId(dto.getRouteId());
		route.setSourcePortId(dto.getSourcePortId());
		route.setDestPortId(dto.getDestPortId());
		route.setName(dto.getName());
		route.setIsDelete(0);

		routeRepository.save(route);

		return true;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		routeRepository.deleteByRouteId(id);
	}

}
