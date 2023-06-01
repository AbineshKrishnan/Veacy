/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.rolerest.controller;

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

import com.training.rolerest.apiresponse.ApiReturnResponse;
import com.training.rolerest.apiresponse.RoleModuleMappingApiResponse;
import com.training.rolerest.model.RoleModuleMappingModel;
import com.training.rolerest.service.RoleModuleMappingService;

/**
 * The RoleModuleMappingController class is a REST controller that handles HTTP requests
 * related to role module mapping management in the application.
 */

@RestController
@RequestMapping("/rolemodulemapping")
public class RoleModuleMappingController implements RoleModuleMappingControllerSwagger{
	
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
	public RoleModuleMappingApiResponse getAllRoleModuleMappings(){
		return roleMappingService.getAllRoleModuleMappings();
	}
	
	/**
     * Get a role module mapping by RoleID.
     *
     * @param id the unique identifier of the role.
     * @return a ResponseEntity containing the Role Module Mapping object if found, or not found status if not found.
     */
	
	@GetMapping("/getbyroleid/{id}")
	public RoleModuleMappingApiResponse getByRoleId(@PathVariable int id){
		return roleMappingService.getByRoleId(id);
	}
	
	/**
     * Get a role module mapping by ModuleID.
     *
     * @param id the unique identifier of the module.
     * @return a ResponseEntity containing the Role Module Mapping object if found, or not found status if not found.
     */
	
	@GetMapping("/getbymoduleid/{id}")
	public RoleModuleMappingApiResponse getBymoduleId(@PathVariable int id){
		return roleMappingService.getBymoduleId(id);
	}
	
	/**
     * Delete a role module mapping by its unique identifier.
     *
     * @param id the unique identifier of the role module mapping.
     * @return a ResponseEntity with no content status.
     */
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiReturnResponse> deleteRoleMapping(@PathVariable int id) {
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
	public ResponseEntity<ApiReturnResponse> update(@PathVariable int id, @RequestBody Map<String, Object> update) {
		return roleMappingService.update(id, update);
	}
}
