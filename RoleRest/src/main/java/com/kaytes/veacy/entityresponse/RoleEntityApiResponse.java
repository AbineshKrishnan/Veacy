package com.kaytes.veacy.entityresponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The RoleEntityApiResponse class is a Model class that is used as response class for API response
 * related to Role management in the application.
 */

@Getter
@Setter
public class RoleEntityApiResponse {
	
	private int id;
	private String name;
	private String description;
}
