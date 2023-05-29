/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.training.RoleRest.entity.Role;
import com.training.RoleRest.model.RoleModel;
import com.training.RoleRest.service.RoleService;

/**
 * The RoleController class is a REST controller that handles HTTP requests
 * related to role management in the application.
 */

@RestController
@RequestMapping("/role")
public class RoleController implements RoleControllerSwagger{
	
	@Autowired
	RoleService roleService;
	
	/**
     * Create a new role.
     *
     * @param role the role object to be created.
     * @return a ResponseEntity containing the created Role object.
     */
	
	@PostMapping
	public ResponseEntity<ApiReturnResponse> createRole(@RequestBody RoleModel roleModel){
		return roleService.createRole(roleModel);
	}
	
	/**
     * Get all roles.
     *
     * @return a ResponseEntity containing a list of all Role objects.
     */
	
	@GetMapping
	public ResponseEntity<List<Role>> getAllRole(){
		List<Role> role = roleService.getAllRole();
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
	/**
     * Get a role by Name.
     *
     * @param name the unique identifier of the role.
     * @return a ResponseEntity containing the Role object if found, or not found status if not found.
     */
	
	@GetMapping("/{name}")
	public ResponseEntity<Optional<Role>> getRoleByName(@PathVariable("name") String name){
		Optional<Role> role = roleService.getRoleByName(name);
		if(role !=null) {
			return ResponseEntity.ok(role);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	/**
     * Update a role's properties given its unique name.
     *
     * @param name the unique name of the user.
     * @param updates a model containing the properties to be updated and their new values.
     * @return the updated Role object.
     */
	
	@PutMapping("/{name}")
	public ResponseEntity<ApiReturnResponse> updateRole(@PathVariable String name, @RequestBody RoleModel roleModel){
		return roleService.updateRole(name, roleModel);
	}
	
	/**
     * Delete a role by its unique name.
     *
     * @param name the unique name of the role.
     * @return a ResponseEntity with no content status.
     */
	
	@DeleteMapping("/{name}")
	public ResponseEntity<ApiReturnResponse> deleteRole(@PathVariable String name) {
		return roleService.deleteRole(name); 
	}
	
	/**
     * Update a Role's properties given its unique name.
     *
     * @param name the unique name of the role.
     * @param updates a map containing the properties to be updated and their new values.
     * @return the updated Role object.
     */
	
	@PatchMapping("/{name}")
	public ResponseEntity<ApiReturnResponse> update(@PathVariable String name,@RequestBody Map<String, Object> updates) {
		return roleService.update(name , updates);
	}
}
