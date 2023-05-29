/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.training.RoleRest.Response.ApiReturnResponse;
import com.training.RoleRest.entity.RoleModuleMapping;
import com.training.RoleRest.model.RoleModuleMappingModel;


public interface RoleModuleMappingService {

	ResponseEntity<ApiReturnResponse> createRoleMapping(RoleModuleMappingModel roleModuleMappingModel);

	List<RoleModuleMapping> getAllRoleModuleMappings();

	ResponseEntity<ApiReturnResponse> updateRoleMapping(int id, RoleModuleMappingModel roleModuleMappingModel);

	ResponseEntity<ApiReturnResponse> deleteRoleMapping(int id);

	List<RoleModuleMapping> getByRoleId(int id);

	List<RoleModuleMapping> getBymoduleId(int id);

	ResponseEntity<ApiReturnResponse> update(int id, Map<String, Object> update);

}
