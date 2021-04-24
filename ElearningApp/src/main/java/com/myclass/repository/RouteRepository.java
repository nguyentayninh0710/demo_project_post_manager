package com.myclass.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Port;
import com.myclass.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>  {

	public Route findByName(String name);

	public Optional<Route> findByRouteId(String routeId);

	public int countByName(String name);

	@Modifying
	@Transactional
	public void deleteByRouteId(String routeId);

}
