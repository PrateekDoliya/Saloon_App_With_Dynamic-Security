package com.employee.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.service.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(nativeQuery = true, value = "select * from employee_service_saloon_app.employee where email=:email")
	Employee getByEmail(String email);
	
}
