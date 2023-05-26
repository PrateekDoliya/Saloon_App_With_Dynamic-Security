package com.security.library.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.library.entitiesDto.EmployeeDto;
import com.security.library.entitiesDto.RoleDto;
import com.security.library.repository.EmployeeDtoRepository;
import com.security.library.repository.RoleDtoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeDtoRepository employeeRepository;
	
	@Autowired
	private RoleDtoRepository roleDtoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("JwtUserDetailsService::User: Loading FROM DB");
		EmployeeDto employee = this.employeeRepository.findByEmail(username);
		log.info("JwtUserDetailsService::User:{}", employee);
		
		RoleDto role = this.roleDtoRepository.getRoleById(employee.getRoleRoleId()); 
		employee.setRoleDto(role);
		log.info("JwtUserDetailsService::User After Role:{}", employee);
		if(employee== null)
			throw new UsernameNotFoundException("Employee Not Found with email : "+username);
		return employee; 
	}

}
