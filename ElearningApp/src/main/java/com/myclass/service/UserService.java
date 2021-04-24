package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CountryDto;
import com.myclass.dto.UserDto;

@Service
public interface UserService {
	List<UserDto> getAll();
	UserDto getById(String id);
	ServiceInfo add(UserDto dto);
	boolean edit(String id, UserDto dto);
	void delete(String id);
}
