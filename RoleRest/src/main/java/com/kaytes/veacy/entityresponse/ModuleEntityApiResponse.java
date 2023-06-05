package com.kaytes.veacy.entityresponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The ModuleEntityApiResponse class is a Model class that is used as response class for API response
 * related to Module management in the application.
 */

@Getter
@Setter
public class ModuleEntityApiResponse {
	
	private int id;
	
	private String moduleName;
	
	private String description;
}