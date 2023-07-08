/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * The RoleModuleMappingModel class is a Model class that is used as request class for API request
 * related to Role Module Mapping management in the application.
 */

@Getter
@Setter
public class RoleModuleMappingModel {
	
	@NotNull
	private Long roleId;
	
	@NotNull
	private Long moduleId;
	
	private boolean createe;
	
	private boolean reead;
	
	private boolean updatee;
	
	private boolean deletee;
	
	private boolean isDeleted = false;
}
