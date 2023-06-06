/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.service;

import java.util.Map;
import org.springframework.http.ResponseEntity;

import com.kaytes.veacy.dto.ApiReturnResponse;
import com.kaytes.veacy.dto.ModuleApiResponse;
import com.kaytes.veacy.model.ModuleModel;

/**
 * The ModuleService interface provides methods for performing CRUD operations
 * and other actions on Module entities.
 */
public interface ModuleService {
	
	/**
     * Save a new module or update an existing one in the data source.
     *
     * @param moduleModel the module object to be saved or updated.
     * @return the saved or updated Module object.
     */
	
	public ResponseEntity<ApiReturnResponse> createModule(ModuleModel moduleModel);

	/**
     * Retrieve all modules from the data source.
     *
     * @return a response of Module objects.
     */
	
	public ResponseEntity<ModuleApiResponse> getAllModule();

	/**
     * Retrieve a module by its unique name.
     *
     * @param moduleName the unique name of the module.
     * @return an Optional object containing the Module if found, or empty if not found.
     */
	
	public ResponseEntity<ModuleApiResponse> getModuleByName(String moduleName);

	/**
     * Delete a module from the data source by its unique name.
     *
     * @param moduleName the unique name of the module.
     */
	
	public ResponseEntity<ApiReturnResponse> deleteModule(String moduleName);

	/**
     * Update a module's properties given its unique name and a map of the updates.
     *
     * @param moduleName the unique name of the role.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated Module object.
     */
	
	ResponseEntity<ApiReturnResponse> update(String moduleName, Map<String, Object> update);
}
