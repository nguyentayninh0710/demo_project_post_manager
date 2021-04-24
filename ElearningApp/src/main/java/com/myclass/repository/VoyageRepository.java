package com.myclass.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Port;
import com.myclass.entity.Voyage;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Integer> {

	public Optional<Voyage> findByVoyageId(String voyageId);
	
	@Modifying
	@Transactional
	public void deleteByVoyageId(String voyageId);

}
