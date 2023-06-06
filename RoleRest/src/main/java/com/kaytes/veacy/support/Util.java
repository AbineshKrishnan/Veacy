/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.support;

import org.springframework.stereotype.Service;

import com.kaytes.veacy.dto.ApiReturnResponse;
import com.kaytes.veacy.dto.ModuleApiResponse;
import com.kaytes.veacy.dto.RoleApiResponse;
import com.kaytes.veacy.dto.RoleModuleMappingApiResponse;

/**
 * The RoleServiceImpl class provides an implementation of the RoleService
 * interface, handling CRUD operations and other actions on Role entities.
 */

@Service
//@Slf4j
public class Util {

	RoleApiResponse roleApiResponse = new RoleApiResponse();
	ModuleApiResponse moduleApiResponse = new ModuleApiResponse();
	RoleModuleMappingApiResponse roleModuleMappingApiResponse = new RoleModuleMappingApiResponse();
	ApiReturnResponse apiReturnResponse = new ApiReturnResponse(); 

	public RoleApiResponse setRoleApiResponseMessage(String message, Boolean status, int code) {
		roleApiResponse.setMessage(message);
		roleApiResponse.setStatus(status);
		roleApiResponse.setStatusCode(code);
		return roleApiResponse;
	}
	
	public ModuleApiResponse setModuleApiResponseMessage(String message, Boolean status, int code) {
		moduleApiResponse.setMessage(message);
		moduleApiResponse.setStatus(status);
		moduleApiResponse.setStatusCode(code);
		return moduleApiResponse;
	}
	
	public RoleModuleMappingApiResponse setRoleModuleMappingApiResponseMessage(String message, Boolean status, int code) {
		roleModuleMappingApiResponse.setMessage(message);
		roleModuleMappingApiResponse.setStatus(status);
		roleModuleMappingApiResponse.setStatusCode(code);
		return roleModuleMappingApiResponse;
	}
	
	public ApiReturnResponse setApiReturnResponseMessage(String message, Boolean status, int code) {
		apiReturnResponse.setMessage(message);
		apiReturnResponse.setStatus(status);
		apiReturnResponse.setStatusCode(code);
		return apiReturnResponse;
	}

}