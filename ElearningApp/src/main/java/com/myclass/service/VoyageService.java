package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.VoyageDto;

@Service
public interface VoyageService {

	List<VoyageDto> getAll();

	VoyageDto getByVoyageId(String voyageId);

	ServiceInfo add(VoyageDto dtoVoyage);

	boolean edit(String voyageId, VoyageDto dtoVoyage);

	void delete(String voyageId);

}
