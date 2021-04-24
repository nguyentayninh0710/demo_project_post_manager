package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.PortDto;

@Service
public interface PortService {
	List<PortDto> getAll();
	PortDto getById(String id);
	ServiceInfo add(PortDto dto);
	boolean edit(String id, PortDto dto);
	void delete(String id);
}
