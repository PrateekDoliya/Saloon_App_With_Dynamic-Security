package com.security.library.entitiesDto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "permission")
public class PermissionDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer permissionId;
	private String permission;
	@JsonIgnore
//	@OneToMany(mappedBy = "permission")
	@Transient
	private Set<RolePermissionDto> rolePermissions = new HashSet<>();
}
