/*
O * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.service;

import java.util.Map;
import org.springframework.http.ResponseEntity;

import com.kaytes.veacy.dto.ApiReturnResponse;
import com.kaytes.veacy.dto.RoleModuleMappingApiResponse;
import com.kaytes.veacy.dto.request.RoleModuleMappingModel;

/**
 * The RoleModuleMappingService interface provides methods for performing CRUD operations
 * and other actions on RoleModuleMapping entities.
 */

public interface RoleModuleMappingService {

	/**
     * Save a new role module mapping or update an existing one in the data source.
     *
     * @param roleModuleMappingModel the role module mapping object to be saved or updated.
     * @return the saved or updated Role Module Mapping object.
     */
	
	ResponseEntity<ApiReturnResponse> createRoleMapping(RoleModuleMappingModel roleModuleMappingModel);

	/**
     * Retrieve all role module mapping from the data source.
     *
     * @return a list of RoleModuleMapping objects.
     */
	
	ResponseEntity<RoleModuleMappingApiResponse> getAllRoleModuleMappings();

	/**
     * Delete a role module mapping from the data source by its unique identifier.
     *
     * @param id the unique identifier of the role module mapping.
     */
	
	ResponseEntity<ApiReturnResponse> deleteRoleMapping(Long id);

	/**
     * Retrieve a role module mapping by its unique identifier roleID.
     *
     * @param id the unique roleID of the role module mapping.
     * @return a List of object containing the Role Module Mapping if found, or empty if not found.
     */
	
	ResponseEntity<RoleModuleMappingApiResponse> getByRoleId(Long id);

	/**
     * Retrieve a role module mapping by its unique identifier moduleID.
     *
     * @param id the unique moduleID of the role module mapping.
     * @return a List of object containing the Role Module Mapping if found, or empty if not found.
     */
	
	ResponseEntity<RoleModuleMappingApiResponse> getBymoduleId(Long id);

	/**
     * Update a roleModuleMapping's properties given its unique identifier and a map of the updates.
     *
     * @param id the unique identifier of the role module mapping.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated Role Module Mapping object.
     */
	
	ResponseEntity<ApiReturnResponse> update(Long id, Map<String, Object> update);

}
