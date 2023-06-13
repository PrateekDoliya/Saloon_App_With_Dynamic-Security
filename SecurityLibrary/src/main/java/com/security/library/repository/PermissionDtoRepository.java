package com.security.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.library.entitiesDto.PermissionDto;

public interface PermissionDtoRepository extends JpaRepository<PermissionDto, Integer> {

	@Query(nativeQuery = true, value="select p.permission_id,p.permission from employee_service_saloon_app.permission p where p.permission_id=:permissionId")
	PermissionDto findByPermissionId(Integer permissionId);
	
}
