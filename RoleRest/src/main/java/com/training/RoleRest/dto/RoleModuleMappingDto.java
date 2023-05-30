package com.training.RoleRest.dto;

import com.training.RoleRest.entity.Module;
import com.training.RoleRest.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleModuleMappingDto {
	
	private Role roleId;
	private Module moduleId;
	private boolean createe;
	private boolean reead;
	private boolean updatee;
	private boolean deletee;
}
