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
import com.training.rolerest.apiresponse.ModuleApiResponse;
import com.training.rolerest.entity.Module;
import com.training.rolerest.model.ModuleModel;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Module Controller" , description = "This Swagger is for Module Controller")
public interface ModuleControllerSwagger {
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Module.class))})})
	public ResponseEntity<ApiReturnResponse> createModule(@RequestBody ModuleModel moduleModel);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Module.class))})})
	public ModuleApiResponse getAllModule();
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Module.class))})})
	public ModuleApiResponse getModuleByName(@PathVariable("moduleName") String ModuleName);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Module.class))})})
	public ResponseEntity<ApiReturnResponse> deleteModule(@PathVariable String moduleName);
	
	@ApiResponses(value = {@ApiResponse(responseCode="200" , description = "Successfully Completed the Task" ,
			content = {@Content(mediaType = "application/json" ,
			schema = @Schema(implementation = Module.class))})})
	public ResponseEntity<ApiReturnResponse> updateModule(@PathVariable String name, @RequestBody Map<String, Object> update);

}
