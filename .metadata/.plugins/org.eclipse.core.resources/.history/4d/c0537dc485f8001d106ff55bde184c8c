package com.services.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.services.service.entities.Services;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
	
	@Query(nativeQuery = true, value = "select * from services s contains s.service= :serviceName")
	List<Services> findServiceByServiceName(String serviceName);
	
}
