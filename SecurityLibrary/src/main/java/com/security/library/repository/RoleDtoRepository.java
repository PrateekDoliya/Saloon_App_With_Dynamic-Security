package com.security.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.library.entitiesDto.RoleDto;

public interface RoleDtoRepository extends JpaRepository<RoleDto, Integer> {

	@Query(nativeQuery = true, value = "select * from maikhane_employee_service_saloon_app.role r where r.role_id=:roleId")
	RoleDto getRoleById(Integer roleId);
	
}
