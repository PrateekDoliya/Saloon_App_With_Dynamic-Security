package com.security.library.jwt;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.library.entitiesDto.EmployeeDto;
import com.security.library.entitiesDto.PermissionDto;
import com.security.library.entitiesDto.RoleDto;
import com.security.library.entitiesDto.RolePermissionDto;
import com.security.library.repository.EmployeeDtoRepository;
import com.security.library.repository.PermissionDtoRepository;
import com.security.library.repository.RoleDtoRepository;
import com.security.library.repository.RolePermissionDtoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeDtoRepository employeeRepository;
	
	@Autowired
	private RoleDtoRepository roleDtoRepository;
	
	@Autowired
	private RolePermissionDtoRepository rolePermissionRepository;
	
	@Autowired
	private PermissionDtoRepository permissionDtoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("JwtUserDetailsService::User: Loading FROM DB");
		EmployeeDto employee = this.employeeRepository.findByEmail(username);
		
		RoleDto role = this.roleDtoRepository.getRoleById(employee.getRoleRoleId()); 
		employee.setRoleDto(role);
		
		Set<RolePermissionDto> employeeRolePermissions = this.rolePermissionRepository.findByEmployeeId(employee.getEmployeeId(), role.getRoleId());
		
		employeeRolePermissions.stream().forEach( (rolePermission) -> {
			PermissionDto permissionDto = this.permissionDtoRepository.findByPermissionId(rolePermission.getPermissionPermissionId());
//			PermissionDto permissionDto = this.permissionDtoRepository.findById(rolePermission.getPermissionPermissionId()).get();
			rolePermission.setPermission(permissionDto);
		});		
		
		employee.getRoleDto().setRolePermissions(employeeRolePermissions);
		log.info("COMPLETE EMPLOYEE::{}", employee);
		
		
//		if(employee == null)
//			throw new UsernameNotFoundException("Employee Not Found with email : "+username);
		return employee; 
	}

}
