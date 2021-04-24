package com.myclass.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.PortDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Port;
import com.myclass.entity.User;
import com.myclass.repository.PortRepository;
import com.myclass.service.PortService;

@Service
public class PortServiceImpl implements PortService {

	@Autowired
	private PortRepository portRepository;
	
	@Override
	public List<PortDto> getAll() {
		List<PortDto> dtos = new ArrayList<PortDto>();
		List<Port> ports = portRepository.findAll();
		for (Port port : ports) {
			dtos.add(new PortDto(port.getPortId(), port.getCode(), 
								 port.getName(), port.getCountry(), port.getIsDelete()));
		}
		return dtos;
		
	}

	@Override
	public PortDto getById(String id) {
		Port port = portRepository.findByPortId(id).get();
		return entityToDto(port);
	}
	private PortDto entityToDto(Port entity) {
		return new PortDto(entity.getPortId(), entity.getCode(), 
				entity.getName(), entity.getCountry(), entity.getIsDelete());
				
			
	}

	@Override
	public ServiceInfo add(PortDto dto) {
		int count = portRepository.countByName(dto.getName());
		if (count > 0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			// nếu chưa thì thêm mới
			Port port = new Port();
			port.setPortId(dto.getPortId());
			port.setCode(dto.getCode());
			port.setName(dto.getName());
			port.setCountry(dto.getCountry());
			port.setIsDelete(0);
			portRepository.save(port);
			return new ServiceInfo(true, "Thêm mới thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(String id, PortDto dto) {
		Optional<Port> optional = portRepository.findByPortId(id);
		if (optional.isPresent()== false) return false;
		Port port = optional.get();
		port.setPortId(dto.getPortId());
		port.setCode(dto.getCode());
		port.setName(dto.getName());
		port.setCountry(dto.getCountry());
		port.setIsDelete(0);
		portRepository.save(port);
		return true;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		portRepository.deleteByPortId(id);
	}

}
