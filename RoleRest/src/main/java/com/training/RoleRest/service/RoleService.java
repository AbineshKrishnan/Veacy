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
import com.training.RoleRest.entity.Role;
import com.training.RoleRest.model.RoleModel;

public interface RoleService {
	
	public ResponseEntity<ApiReturnResponse> createRole(RoleModel roleModel);

	public List<Role> getAllRole();

	public Optional<Role> getRoleByName(String name);

	public ResponseEntity<ApiReturnResponse> updateRole(String name, RoleModel roleModel);

	public ResponseEntity<ApiReturnResponse> deleteRole(String name);

	public ResponseEntity<ApiReturnResponse> update(String name, Map<String, Object> updates);
}
