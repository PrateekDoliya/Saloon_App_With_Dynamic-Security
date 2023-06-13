package com.auth.service.entities;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String firstName;
	private String lastName;
	@Column(unique = true, nullable = false )
	private String email;
	
	@Column(unique = true, nullable = false)
	private String password;
	
	private String mobileNumber;
	private LocalDate dateOfBirth;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Role role;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<SimpleGrantedAuthority> authorities = 
				role.getRolePermissions().stream().map( (permission) -> new SimpleGrantedAuthority(permission.getPermission().getPermission())).collect(Collectors.toSet());
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		return authorities;
		
//		Set<SimpleGrantedAuthority> authorities = role.getRolePermissions().stream().map( SimpleGrantedAuthority :: new).collect(Collectors.toSet());
//		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//		return authorities;
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
