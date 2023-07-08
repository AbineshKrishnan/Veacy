/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The RoleModuleMappingEntityApiResponse class is a Model class that is used as response class for API response
 * related to Role Module Mapping management in the application.
 */

@Getter
@Setter
public class RoleModuleMappingResponse {
	
	private Long id;
	private String role;	
	private String module;	
	private boolean createe;	
	private boolean reead;	
	private boolean updatee;	
	private boolean deletee;
	
}
