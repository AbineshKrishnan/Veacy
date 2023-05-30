package com.training.RoleRest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleModuleMappingEntityApiResponse {
	
	private String role;	
	private String module;	
	private boolean createe;	
	private boolean reead;	
	private boolean updatee;	
	private boolean deletee;
	
}
