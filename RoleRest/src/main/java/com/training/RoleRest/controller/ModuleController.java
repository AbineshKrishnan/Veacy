/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.RoleRest.Response.ApiReturnResponse;
import com.training.RoleRest.Response.ModuleApiResponse;
import com.training.RoleRest.model.ModuleModel;
import com.training.RoleRest.service.ModuleService;

/**
 * The ModuleController class is a REST controller that handles HTTP requests
 * related to module management in the application.
 */

@RestController
@RequestMapping("/module")
public class ModuleController implements ModuleControllerSwagger{
	
	@Autowired
	ModuleService moduleService;
	
	/**
     * Create a new m.
     *
     * @param module the module object to be created.
     * @return a ResponseEntity containing the created Module object.
     */
	
	@PostMapping
	public ResponseEntity<ApiReturnResponse> createModule(@RequestBody ModuleModel moduleModel) {
		return moduleService.createModule(moduleModel);
		
	}
	
	/**
     * Get all modules.
     *
     * @return a ResponseEntity containing of all Module objects.
     */
	
	@GetMapping
	public ModuleApiResponse getAllModule(){
		return moduleService.getAllModule();
	}
	
	/**
     * Get a module by Name.
     *
     * @param name the unique identifier of the module.
     * @return a ResponseEntity containing the Module object if found, or not found status if not found.
     */
	
	@GetMapping("/{moduleName}")
	public ModuleApiResponse getModuleByName(@PathVariable("moduleName") String ModuleName){
		return moduleService.getModuleByName(ModuleName);
	}
	
	/**
     * Update a module's properties given its unique identifier.
     *
     * @param id the unique identifier of the module.
     * @param updates a model containing the properties to be updated and their new values.
     * @return the updated Module object.
     */
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiReturnResponse> updateModule(@PathVariable int id,@RequestBody ModuleModel moduleModel){
		return moduleService.updateModule(id,moduleModel);
	}
	
	/**
     * Delete a module by its unique name.
     *
     * @param name the unique name of the module.
     * @return a ResponseEntity with no content status.
     */
	
	@DeleteMapping("/{moduleName}")
	public ResponseEntity<ApiReturnResponse> deleteModule(@PathVariable String moduleName) {
		return moduleService.deleteModule(moduleName);
	}
	
	/**
     * Update a module's properties given its unique name.
     *
     * @param name the unique name of the module.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated Module object.
     */
	
	@PatchMapping("/{moduleName}")
	public ResponseEntity<ApiReturnResponse> updateModule(@PathVariable String moduleName, @RequestBody Map<String, Object> update) {
		return moduleService.update(moduleName, update);
	}
}
