package com.employee.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.service.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
