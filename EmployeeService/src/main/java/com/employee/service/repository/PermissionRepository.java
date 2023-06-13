package com.employee.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.service.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{

}
