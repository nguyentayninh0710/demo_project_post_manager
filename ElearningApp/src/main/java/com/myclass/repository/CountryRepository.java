package com.myclass.repository;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.myclass.entity.Country;

@Component
public interface CountryRepository extends JpaRepository<Country, Integer> {
	public Country findByName(String name);
	public Optional<Country> findById(String id);
	
	public int countByName(String name);
	
	 
	@Modifying
    @Transactional
	 public void deleteById(String id);
}
