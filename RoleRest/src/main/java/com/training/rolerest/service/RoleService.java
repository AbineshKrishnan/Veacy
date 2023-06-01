/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.rolerest.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.training.rolerest.apiresponse.ApiReturnResponse;
import com.training.rolerest.apiresponse.RoleApiResponse;
import com.training.rolerest.model.RoleModel;

/**
 * The RoleService interface provides methods for performing CRUD operations
 * and other actions on ROle entities.
 */

public interface RoleService {
	
	/**
     * Save a new role or update an existing one in the data source.
     *
     * @param roleModel the role object to be saved or updated.
     * @return the saved or updated Role object.
     */
	
	public ResponseEntity<ApiReturnResponse> createRole(RoleModel roleModel);

	/**
     * Retrieve all roles from the data source.
     *
     * @return a list of Role objects.
     */
	
	public ApiReturnResponse  getAllRole();

	/**
     * Retrieve a role by its unique name.
     *
     * @param name the unique name of the role.
     * @return an Optional object containing the Role if found, or empty if not found.
     */
	
	public RoleApiResponse getRoleByName(String name);

	public ResponseEntity<ApiReturnResponse> updateRole(String name, RoleModel roleModel);

	/**
     * Delete a role from the data source by its unique name.
     *
     * @param name the unique name of the role.
     */
	
	public ResponseEntity<ApiReturnResponse> deleteRole(String name);

	/**
     * Update a role's properties given its unique name and a map of the updates.
     *
     * @param name the unique name of the role.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated Role object.
     */
	
	public ResponseEntity<ApiReturnResponse> update(String name, Map<String, Object> updates);
}
