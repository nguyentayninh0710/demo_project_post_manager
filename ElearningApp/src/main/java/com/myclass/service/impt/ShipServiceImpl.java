package com.myclass.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.ShipDto;
import com.myclass.entity.Ship;
import com.myclass.repository.ShipRepository;
import com.myclass.service.ShipService;

@Service
public class ShipServiceImpl implements ShipService {
	@Autowired
	private ShipRepository shipRepository;

	public List<ShipDto> getAll() {
		List<ShipDto> dtoShip = new ArrayList<ShipDto>();
		// Gọi phương thức của Repository để truy vấn dữ liệu
		List<Ship> ships = shipRepository.findAll();
		// Ánh xạ dữ liệu từ Entity qua DTO
		for (Ship ship : ships) {
			dtoShip.add(new ShipDto(ship.getShipId(), ship.getName(), ship.getCapacity(), ship.getIsDelete()));
		}
		return dtoShip;
	}

	@Override
	public ServiceInfo add(ShipDto dtoShip) {
		int count = shipRepository.countByName(dtoShip.getName());
		if (count > 0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			// nếu chưa thì thêm mới
			Ship ship = new Ship();
			ship.setName(dtoShip.getName());
			ship.setShipId(dtoShip.getShipId());
			ship.setCapacity(dtoShip.getCapacity());
			ship.setIsDelete(0);
			shipRepository.save(ship);
			return new ServiceInfo(true, "Thêm mới thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public ShipDto getById(String shipId) {
		Ship ship = shipRepository.findByShipId(shipId).get();
		return entityToDto(ship);
	}

	private ShipDto entityToDto(Ship entity) {
		return new ShipDto(entity.getShipId(), entity.getName(), entity.getCapacity(), 0);
	}

	@Override
	public boolean edit(String id, ShipDto dtoShip) {
		Optional<Ship> optional = shipRepository.findByShipId(id);
		if (optional.isPresent() == false)
			return false;
		Ship ship = optional.get();
		ship.setName(dtoShip.getName());
		ship.setShipId(dtoShip.getShipId());
		ship.setCapacity(dtoShip.getCapacity());
		ship.setIsDelete(0);

		shipRepository.save(ship);

		return true;
	}

	@Override
	public void delete(String id) {
		shipRepository.deleteByShipId(id);
	}

}
