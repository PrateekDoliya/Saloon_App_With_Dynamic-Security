package com.security.library.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.library.entitiesDto.RolePermissionDto;

public interface RolePermissionDtoRepository extends JpaRepository<RolePermissionDto, Integer> {

	@Query(nativeQuery = true, value = "select r.id,r.employee_id, r.permission_permission_id from maikhane_employee_service_saloon_app.role_permissions r where r.employee_id=:employeeId and r.role_role_id=:roleId")
	Set<RolePermissionDto> findByEmployeeId(Integer employeeId, Integer roleId);
}
