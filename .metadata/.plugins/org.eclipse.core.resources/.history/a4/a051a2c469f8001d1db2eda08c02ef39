package com.security.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.library.entitiesDto.EmployeeDto;

public interface EmployeeDtoRepository extends JpaRepository<EmployeeDto, Integer> {

	@Query(nativeQuery = true, value=" select * from employee_service_saloon_app.employee where email=:email")
	EmployeeDto findByEmail(String email);
}
