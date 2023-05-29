/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.RoleRest.Response.ApiReturnResponse;
import com.training.RoleRest.entity.RoleModuleMapping;
import com.training.RoleRest.model.RoleModuleMappingModel;
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
	public ResponseEntity<List<RoleModuleMapping>> getAllRoleModuleMappings();
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<List<RoleModuleMapping>> getByRoleId(@PathVariable int id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<List<RoleModuleMapping>> getBymoduleId(@PathVariable int id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> updateRoleMapping(@PathVariable int id, @RequestBody RoleModuleMappingModel roleModuleMappingModel);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> deleteRoleMapping(@PathVariable int id);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = RoleModuleMapping.class))})})
	public ResponseEntity<ApiReturnResponse> update(@PathVariable int id, @RequestBody Map<String, Object> update);

}