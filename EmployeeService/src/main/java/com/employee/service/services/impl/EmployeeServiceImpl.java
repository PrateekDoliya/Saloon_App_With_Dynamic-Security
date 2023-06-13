package com.employee.service.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.service.entities.Employee;
import com.employee.service.entities.Role;
import com.employee.service.entities.RolePermission;
import com.employee.service.exception.ResourceNotFoundException;
import com.employee.service.repository.EmployeeRepository;
import com.employee.service.repository.RolePermissionRepository;
import com.employee.service.repository.RoleRepository;
import com.employee.service.requestDto.EmployeeRequestDto;
import com.employee.service.responseDto.EmployeeResponseDto;
import com.employee.service.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
		
		Role role = this.roleRepository.findById(employeeRequestDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role", "role_id", employeeRequestDto.getRoleId()));
		Employee employee = this.mapper.map(employeeRequestDto, Employee.class);
		employee.setRole(role);
		Employee savedEmployee = this.employeeRepository.save(employee);
		return this.mapper.map(savedEmployee, EmployeeResponseDto.class);
	}

	@Override
	public List<EmployeeResponseDto> getAllEmployees() {
		List<Employee> allEmployees = this.employeeRepository.findAll();
		return allEmployees.stream().map( (emp) -> this.mapper.map(emp, EmployeeResponseDto.class)).collect(Collectors.toList());
	}

	@Override
	public EmployeeResponseDto getEmployeeById(Integer employeeId) {
		Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "employee_id", employeeId));
		Set<RolePermission> employeeRolePermissions = this.rolePermissionRepository.findByEmployeeId(employeeId);
		employee.getRole().setRolePermissions(employeeRolePermissions);
		return this.mapper.map(employee, EmployeeResponseDto.class);
	}

	@Override
	public EmployeeResponseDto getEmployeeByEmail(String email) {
		Employee employee = this.employeeRepository.getByEmail(email);
		if(employee != null ) {
			return this.mapper.map(employee, EmployeeResponseDto.class);
		}else {
			throw new ResourceNotFoundException("Employee", "email_id", email);
		}
	}	
}
