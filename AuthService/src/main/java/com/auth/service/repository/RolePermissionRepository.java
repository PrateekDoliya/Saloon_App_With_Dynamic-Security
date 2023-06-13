package com.auth.service.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.service.entities.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {
	Set<RolePermission> findByEmployeeId(Integer employeeId);
}
