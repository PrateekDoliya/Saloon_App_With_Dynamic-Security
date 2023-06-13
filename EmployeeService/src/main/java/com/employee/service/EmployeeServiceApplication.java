package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.employee.service.entities.Permission;
import com.employee.service.entities.Role;
import com.employee.service.repository.PermissionRepository;
import com.employee.service.repository.RoleRepository;

@SpringBootApplication
@ComponentScan({"com.employee.service", "com.security.library"})
public class EmployeeServiceApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Role role1 = new Role(); role1.setRoleName("ROLE_ADMIN");
		 * this.roleRepository.save(role1);
		 * 
		 * Role role2 = new Role(); role2.setRoleName("ROLE_HR");
		 * this.roleRepository.save(role2);
		 * 
		 * Role role3 = new Role(); role3.setRoleName("ROLE_CASHIER");
		 * this.roleRepository.save(role3);
		 * 
		 * 
		 * Permission permission1 = new Permission();
		 * permission1.setPermission("ADMIN_CREATE_EMPLOYEE");
		 * this.permissionRepository.save(permission1);
		 * 
		 * Permission permission2 = new Permission();
		 * permission2.setPermission("ADMIN_READ_EMPLOYEE");
		 * this.permissionRepository.save(permission2);
		 * 
		 * Permission permission3 = new Permission();
		 * permission3.setPermission("ADMIN_UPDATE_EMPLOYEE");
		 * this.permissionRepository.save(permission3);
		 * 
		 * Permission permission4 = new Permission();
		 * permission4.setPermission("ADMIN_DELETE_EMPLOYEE");
		 * this.permissionRepository.save(permission4);
		 * 
		 * Permission permission5 = new Permission();
		 * permission5.setPermission("ADMIN_CREATE_SERVICE");
		 * this.permissionRepository.save(permission5);
		 * 
		 * Permission permission6 = new Permission();
		 * permission6.setPermission("ADMIN_UPDATE_SERVICE");
		 * this.permissionRepository.save(permission6);
		 * 
		 * Permission permission7 = new Permission();
		 * permission7.setPermission("ADMIN_DELETE_SERVICE");
		 * this.permissionRepository.save(permission7);
		 * 
		 * Permission permission8 = new Permission();
		 * permission8.setPermission("READ_SERVICE");
		 * this.permissionRepository.save(permission8);
		 * 
		 */
		
	}

}
