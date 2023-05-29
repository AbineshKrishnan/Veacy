/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.training.RoleRest.Response.ApiReturnResponse;
import com.training.RoleRest.entity.Module;
import com.training.RoleRest.model.ModuleModel;

public interface ModuleService {
	
	public ResponseEntity<ApiReturnResponse> createModule(ModuleModel moduleModel);

	public List<Module> getAllModule();

	public Optional<Module> getModuleByName(String moduleName);

	public ResponseEntity<ApiReturnResponse> updateModule(int id, ModuleModel moduleModel);

	public ResponseEntity<ApiReturnResponse> deleteModule(String moduleName);

	ResponseEntity<ApiReturnResponse> update(String moduleName, Map<String, Object> update);
}
