package com.auth.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.service.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByEmail(String email);
	
}
