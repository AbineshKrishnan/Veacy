/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaytes.veacy.controller.RoleModuleMappingController;
import com.kaytes.veacy.dto.ApiReturnResponse;
import com.kaytes.veacy.dto.RoleModuleMappingApiResponse;
import com.kaytes.veacy.dto.request.RoleModuleMappingModel;
import com.kaytes.veacy.service.RoleModuleMappingService;

/**
 * The RoleModuleMappingControllerImpl class is a REST controller that handles HTTP requests
 * related to role module mapping management in the application.
 */

@RestController
@RequestMapping("/rolemodulemapping")
public class RoleModuleMappingControllerImpl implements RoleModuleMappingController{
	
	@Autowired
	RoleModuleMappingService roleMappingService;
	
	/**
     * Create a new role module mapping.
     *
     * @param role module mapping the role module mapping object to be created.
     * @return a ResponseEntity containing the created Role Module Mapping object.
     */
	
	@PostMapping
	public ResponseEntity<ApiReturnResponse> createRoleMapping(@RequestBody RoleModuleMappingModel roleModuleMappingModel){
		return roleMappingService.createRoleMapping(roleModuleMappingModel);
	}
	
	/**
     * Get all Role Module Mappings.
     *
     * @return a ResponseEntity containing a list of all Role Module Mapping objects.
     */
	
	@GetMapping
	public ResponseEntity<RoleModuleMappingApiResponse> getAllRoleModuleMappings(){
		return roleMappingService.getAllRoleModuleMappings();
	}
	
	/**
     * Get a role module mapping by RoleID.
     *
     * @param id the unique identifier of the role.
     * @return a ResponseEntity containing the Role Module Mapping object if found, or not found status if not found.
     */
	
	@GetMapping("/getbyroleid/{id}")
	public ResponseEntity<RoleModuleMappingApiResponse> getByRoleId(@PathVariable Long id){
		return roleMappingService.getByRoleId(id);
	}
	
	/**
     * Get a role module mapping by ModuleID.
     *
     * @param id the unique identifier of the module.
     * @return a ResponseEntity containing the Role Module Mapping object if found, or not found status if not found.
     */
	
	@GetMapping("/getbymoduleid/{id}")
	public ResponseEntity<RoleModuleMappingApiResponse> getBymoduleId(@PathVariable Long id){
		return roleMappingService.getBymoduleId(id);
	}
	
	/**
     * Delete a role module mapping by its unique identifier.
     *
     * @param id the unique identifier of the role module mapping.
     * @return a ResponseEntity with no content status.
     */
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiReturnResponse> deleteRoleMapping(@PathVariable Long id) {
		return roleMappingService.deleteRoleMapping(id);
	}
	
	/**
     * Update a role module mapping's properties given its unique identifier.
     *
     * @param id the unique identifier of the role module mapping.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated ROle Module Mapping object.
     */
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiReturnResponse> update(@PathVariable Long id, @RequestBody Map<String, Object> update) {
		return roleMappingService.update(id, update);
	}
}
