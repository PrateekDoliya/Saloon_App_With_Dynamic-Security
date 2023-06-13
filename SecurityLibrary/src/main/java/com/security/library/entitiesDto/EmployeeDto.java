package com.security.library.entitiesDto;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class EmployeeDto implements UserDetails {

	@Id
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobileNumber;
	private LocalDate dateOfBirth;
	@Transient
	private RoleDto roleDto;
	
	@Column(name = "role_role_id", insertable = false  )
	private Integer roleRoleId;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<SimpleGrantedAuthority> authorities = 
				roleDto.getRolePermissions().stream().map( (permission) -> new SimpleGrantedAuthority(permission.getPermission().getPermission())).collect(Collectors.toSet());
		authorities.add(new SimpleGrantedAuthority(roleDto.getRoleName()));
		return authorities;
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
