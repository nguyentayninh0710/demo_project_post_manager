package com.myclass.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.myclass.entity.Port;
import com.myclass.entity.User;

public interface PortRepository  extends JpaRepository<Port, Integer>{
	public Optional<Port> findByPortId(String portId);
	public int countByName(String name);
	
	@Modifying
    @Transactional
	 public void deleteByPortId(String id);
}
