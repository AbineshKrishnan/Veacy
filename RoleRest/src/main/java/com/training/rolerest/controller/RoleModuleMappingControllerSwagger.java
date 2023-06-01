/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.rolerest.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.rolerest.apiresponse.ApiReturnResponse;
import com.training.rolerest.apiresponse.RoleModuleMappingApiResponse;
import com.training.rolerest.entity.RoleModuleMapping;
import com.training.rolerest.model.RoleModuleMappingModel;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RoleModuleMapping Controller" , description = "This Swagger is for RoleModuleMapping Controller")
public interface RoleModuleMappingControllerSwagger {
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> createRoleMapping(@RequestBody RoleModuleMappingModel roleModuleMappingModel);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public RoleModuleMappingApiResponse getAllRoleModuleMappings();
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public RoleModuleMappingApiResponse getByRoleId(@PathVariable int id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public RoleModuleMappingApiResponse getBymoduleId(@PathVariable int id);
		
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> deleteRoleMapping(@PathVariable int id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> update(@PathVariable int id, @RequestBody Map<String, Object> update);

}