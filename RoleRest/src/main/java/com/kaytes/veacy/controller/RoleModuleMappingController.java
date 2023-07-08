/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.kaytes.veacy.dto.ApiReturnResponse;
import com.kaytes.veacy.dto.RoleModuleMappingApiResponse;
import com.kaytes.veacy.dto.request.RoleModuleMappingModel;
import com.kaytes.veacy.entity.RoleModuleMapping;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * The RoleModuleMappingController interface is used for customizing the Swagger
 */


@Tag(name = "RoleModuleMapping Controller" , description = "This Swagger is for RoleModuleMapping Controller")
public interface RoleModuleMappingController {
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> createRoleMapping(@RequestBody RoleModuleMappingModel roleModuleMappingModel);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<RoleModuleMappingApiResponse> getAllRoleModuleMappings();
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<RoleModuleMappingApiResponse> getByRoleId(@PathVariable Long id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<RoleModuleMappingApiResponse> getBymoduleId(@PathVariable Long id);
		
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> deleteRoleMapping(@PathVariable Long id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> update(@PathVariable Long id, @RequestBody Map<String, Object> update);

}