/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * The RoleModel class is a Model class that is used as request class for API request
 * related to Role management in the application.
 */

@Getter
@Setter
public class RoleModel {
	
	
	private String name;
	private String description;
	private boolean isActive=true;
}
