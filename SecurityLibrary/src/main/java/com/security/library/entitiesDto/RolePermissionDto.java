package com.security.library.entitiesDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "role_permissions")
public class RolePermissionDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@ManyToOne(targetEntity = RoleDto.class)
//	@Transient
//	private RoleDto role;
	
	private Integer employeeId;
	@Column(name = "permission_permission_id", insertable = false  )
	private Integer permissionPermissionId;
	
//	@ManyToOne(targetEntity = PermissionDto.class)
	@Transient
	private PermissionDto permission;
	
}
