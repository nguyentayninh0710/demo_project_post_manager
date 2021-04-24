package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CountryDto;
import com.myclass.entity.Country;

@Service
public interface CountryService {
	List<CountryDto> getAll();
	CountryDto getById(String id);
	ServiceInfo add(CountryDto dto);
	boolean edit(String id, CountryDto dto);
	void delete(String id);
}
