package com.employee.service.services.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.service.entities.Employee;
import com.employee.service.entities.Permission;
import com.employee.service.entities.Role;
import com.employee.service.entities.RolePermission;
import com.employee.service.exception.ResourceNotFoundException;
import com.employee.service.repository.EmployeeRepository;
import com.employee.service.repository.PermissionRepository;
import com.employee.service.repository.RolePermissionRepository;
import com.employee.service.repository.RoleRepository;
import com.employee.service.requestDto.EmployeePermissionRequestDto;
import com.employee.service.responseDto.EmployeePermissionResponseDto;
import com.employee.service.responseDto.EmployeeResponseDto;
import com.employee.service.responseDto.PermissionResponseDto;
import com.employee.service.responseDto.RoleResponseDto;
import com.employee.service.services.EmployeePermissionService;

@Service
public class EmployeePermissionServiceImpl implements EmployeePermissionService {

	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public EmployeeResponseDto grantPermission(Integer employeeId, EmployeePermissionRequestDto employeePermissionRequestDto) {
		
		Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "employee_id", employeeId));
		
		Role role = this.roleRepository.findById(employeePermissionRequestDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role", "role_id", employeePermissionRequestDto.getRoleId()));

		Set<RolePermission> permissionsDtos = new HashSet<>();
		employeePermissionRequestDto.getPermissions().stream().forEach( (permissionId) -> {
			Permission permission = this.permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFoundException("Permission", "permission_id", permissionId));
			RolePermission rolePermission = new RolePermission();
			rolePermission.setRole(role);
			rolePermission.setPermission(permission);
			rolePermission.setEmployeeId(employeeId);
			RolePermission savedPermission = this.rolePermissionRepository.save(rolePermission);
	
			permissionsDtos.add(savedPermission);
		});
		
		employee.setRole(role);
		employee.getRole().setRolePermissions(permissionsDtos);	
		
		return this.mapper.map(employee, EmployeeResponseDto.class);
	}

}
