/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleModuleMappingModel {
	
	@NotNull
	private int roleId;
	
	@NotNull
	private int moduleId;
	
	private boolean createe;
	
	private boolean reead;
	
	private boolean updatee;
	
	private boolean deletee;
	
}
