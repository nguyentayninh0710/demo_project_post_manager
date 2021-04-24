package com.myclass.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CountryDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Country;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDto> getAll() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		List<User> users = userRepository.findAll();
		// Ánh xạ dữ liệu từ Entity qua DTO
		for (User user : users) {
			dtos.add(new UserDto(user.getUserId(), user.getLoginName(), user.getFristName(), 
					user.getLastName(), user.getPhone(),user.getEmail(), 
					user.getAddress(), user.getCity(), user.getState(), 
					user.getPostalCode(), user.getCountry(), user.getRole(), user.getIsDelete()));
		}
		return dtos;
		
	}

	@Override
	public UserDto getById(String id) {
		User user = userRepository.findByUserId(id).get();
		return entityToDto(user);
	}
	private UserDto entityToDto(User entity) {
		return new UserDto(entity.getUserId(), entity.getLoginName(), entity.getFristName(), 
				entity.getLastName(), entity.getPhone(),entity.getEmail(), 
				entity.getAddress(), entity.getCity(), entity.getState(), 
				entity.getPostalCode(), entity.getCountry(), entity.getRole(), 0);
				
			
	}

	@Override
	public ServiceInfo add(UserDto dto) {
		int count = userRepository.countByLoginName(dto.getLoginName());
		if (count > 0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			// nếu chưa thì thêm mới
			User user = new User();
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12));
			user.setUserId(dto.getUserId());
			user.setLoginName(dto.getLoginName());
			user.setFristName(dto.getFristName());
			user.setLastName(dto.getLastName());
			user.setPassword(hashed);
			user.setPhone(dto.getPhone());
			user.setEmail(dto.getEmail());
			user.setAddress(dto.getAddress());
			user.setCity(dto.getCity());
			user.setState(dto.getState());
			user.setPostalCode(dto.getPostalCode());
			user.setCountry(dto.getCountry());
			user.setRole(dto.getRole());
			user.setIsDelete(0);
			userRepository.save(user);
			return new ServiceInfo(true, "Thêm mới thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(String id, UserDto dto) {
		Optional<User> optional = userRepository.findByUserId(id);
		if (optional.isPresent()== false) return false;
		User user = optional.get();
		user.setUserId(dto.getUserId());
		user.setLoginName(dto.getLoginName());
		user.setFristName(dto.getFristName());
		user.setLastName(dto.getLastName());		
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setAddress(dto.getAddress());
		user.setCity(dto.getCity());
		user.setState(dto.getState());
		user.setPostalCode(dto.getPostalCode());
		user.setCountry(dto.getCountry());
		user.setRole(dto.getRole());
		user.setIsDelete(0);
		userRepository.save(user);
		
		return true;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		userRepository.deleteByUserId(id);
	}

}
