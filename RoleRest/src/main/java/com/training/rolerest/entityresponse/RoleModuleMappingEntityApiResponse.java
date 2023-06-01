package com.training.rolerest.entityresponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The RoleModuleMappingEntityApiResponse class is a Model class that is used as response class for API response
 * related to Role Module Mapping management in the application.
 */

@Getter
@Setter
public class RoleModuleMappingEntityApiResponse {
	
	private int id;
	private String role;	
	private String module;	
	private boolean createe;	
	private boolean reead;	
	private boolean updatee;	
	private boolean deletee;
	
}
