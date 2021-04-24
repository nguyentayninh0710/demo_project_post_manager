package com.myclass.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myclass.common.ServiceInfo;
import com.myclass.dto.CountryDto;
import com.myclass.entity.Country;
import com.myclass.repository.CountryRepository;
import com.myclass.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository countryRepository;

	public List<CountryDto> getAll() {
		List<CountryDto> dtos = new ArrayList<CountryDto>();
		// Gọi phương thức của Repository để truy vấn dữ liệu
		List<Country> countrys = countryRepository.findAll();
		// Ánh xạ dữ liệu từ Entity qua DTO
		for (Country country : countrys) {
			dtos.add(new CountryDto(country.getId(), country.getName(), country.getIsDelete()));
		}
		return dtos;
	}

	@Override
	public ServiceInfo add(CountryDto dto) {
		int count = countryRepository.countByName(dto.getName());
		if (count > 0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			// nếu chưa thì thêm mới
			Country country = new Country();
			country.setName(dto.getName());
			country.setId(dto.getId());
			country.setIsDelete(0);
			countryRepository.save(country);
			return new ServiceInfo(true, "Thêm mới thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}



	@Override
	public CountryDto getById(String id) {
		Country country = countryRepository.findById(id).get();
		return entityToDto(country);
	}
	private CountryDto entityToDto(Country entity) {
		return new CountryDto(
				entity.getId(),
				entity.getName(),
				0
			);
	}
	@Override
	public boolean edit(String id, CountryDto dto) {
		Optional<Country> optional = countryRepository.findById(id);
		if (optional.isPresent()== false) return false;
		Country country = optional.get();
		country.setId(dto.getId());
		country.setName(dto.getName());
		country.setIsDelete(0);
		
		countryRepository.save(country);
		
		return true;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		countryRepository.deleteById(id);
	}




	


}
