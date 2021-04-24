package com.myclass.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Port;
import com.myclass.entity.Ship;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Integer>  {

	public Ship findByName(String name);

	public Optional<Ship> findByShipId(String shipId);

	public int countByName(String name);

	@Modifying
	@Transactional
	public void deleteByShipId(String shipId);

}
