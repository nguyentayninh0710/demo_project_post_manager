package com.myclass.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CountryDto;
import com.myclass.dto.VoyageDto;
import com.myclass.entity.Country;
import com.myclass.entity.Voyage;
import com.myclass.repository.CountryRepository;
import com.myclass.repository.VoyageRepository;
import com.myclass.service.CountryService;
import com.myclass.service.VoyageService;

@Service
public class VoyageServiceImpl implements VoyageService {
	@Autowired
	private VoyageRepository voyageRepository;

	public List<VoyageDto> getAll() {
		List<VoyageDto> dtoVoyage = new ArrayList<VoyageDto>();
		// Gọi phương thức của Repository để truy vấn dữ liệu
		List<Voyage> voyages = voyageRepository.findAll();
		// Ánh xạ dữ liệu từ Entity qua DTO
		for (Voyage voyage : voyages) {
			dtoVoyage.add(new VoyageDto(voyage.getVoyageId(), voyage.getShipId(), voyage.getRouteId(),
					voyage.getStartDate(), voyage.getEndDate(), voyage.getVoyageQuantity(), voyage.getCostPreTeq(),
					voyage.getIsDelete()));
		}
		return dtoVoyage;
	}

	@Override
	public VoyageDto getByVoyageId(String voyageId) {
		Voyage voyage = voyageRepository.findByVoyageId(voyageId).get();
		return entityToDto(voyage);
	}

	private VoyageDto entityToDto(Voyage voyage) {
		return new VoyageDto(voyage.getVoyageId(), voyage.getShipId(), voyage.getRouteId(), voyage.getStartDate(),
				voyage.getEndDate(), voyage.getVoyageQuantity(), voyage.getCostPreTeq(), 0);
	}

	@Override
	public boolean edit(String voyageId, VoyageDto dtoVoyage) {
		Optional<Voyage> optional = voyageRepository.findByVoyageId(voyageId);
		if (optional.isPresent() == false)
			return false;
		Voyage voyage = optional.get();
		voyage.setVoyageId(dtoVoyage.getVoyageId());
		voyage.setShipId(dtoVoyage.getShipId());
		voyage.setRouteId(dtoVoyage.getRouteId());
		voyage.setStartDate(dtoVoyage.getStartDate());
		voyage.setEndDate(dtoVoyage.getEndDate());
		voyage.setVoyageQuantity(dtoVoyage.getVoyageQuantity());
		voyage.setCostPreTeq(dtoVoyage.getCostPreTeq());
		voyage.setIsDelete(0);

		voyageRepository.save(voyage);

		return true;
	}

	@Override
	public void delete(String id) {
		voyageRepository.deleteByVoyageId(id);
	}

	@Override
	public ServiceInfo add(VoyageDto dtoVoyage) {
		// TODO Auto-generated method stub
		return null;
	}



}
