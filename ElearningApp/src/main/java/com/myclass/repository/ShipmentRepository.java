package com.myclass.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import com.myclass.entity.Country;
import com.myclass.entity.Shipment;
import com.myclass.entity.User;

@Component
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
	public Optional<Shipment> findByShipmentId(String shipmentId);
	public int countByShipmentId(String shipmentId);
	//public User findByLoginName(String loginName);
	
	@Modifying
    @Transactional
	public void deleteByShipmentId(String id);
}