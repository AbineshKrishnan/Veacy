/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.RoleRest.Response.ApiReturnResponse;
import com.training.RoleRest.Response.MessageProperties;
import com.training.RoleRest.Response.RoleModuleMappingApiResponse;
import com.training.RoleRest.entity.Module;
import com.training.RoleRest.entity.Role;
import com.training.RoleRest.entity.RoleModuleMapping;
import com.training.RoleRest.model.RoleModuleMappingModel;
import com.training.RoleRest.repository.RoleModuleMappingRepository;
import com.training.RoleRest.service.RoleModuleMappingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleModuleMappingServiceImpl implements RoleModuleMappingService {

	@Autowired
	RoleModuleMappingRepository roleModuleMappingRepository;
	
	ApiReturnResponse apiReturnResponse = new ApiReturnResponse();
	
	@Autowired
	MessageProperties messageProperties;
	
	@Override
	public ResponseEntity<ApiReturnResponse> createRoleMapping(RoleModuleMappingModel roleModuleMappingModel) {
		log.info("Entered into createRoleMapping method");
		if(roleModuleMappingRepository.getRoleIdAndModuleId(roleModuleMappingModel.getModuleId(),roleModuleMappingModel.getRoleId()).isEmpty()) {
		try {
			RoleModuleMapping roleModuleMapping = new RoleModuleMapping();
			BeanUtils.copyProperties(roleModuleMappingModel, roleModuleMapping);
			Role role = new Role();
			Module module = new Module();
			role.setId(roleModuleMappingModel.getRoleId());
			module.setId(roleModuleMappingModel.getModuleId());
			roleModuleMapping.setRoleId(role);
			roleModuleMapping.setModuleId(module);
			roleModuleMappingRepository.save(roleModuleMapping);
			log.debug("Saving RoleModuleMapping {} data to the database ",roleModuleMappingModel);
			apiReturnResponse.setStatus(Boolean.TRUE);
			apiReturnResponse.setMessage(messageProperties.getSUCCESS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role is not created");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getERROR_CODE_500());
			apiReturnResponse.setStatusCode(messageProperties.getINERNAL_SERVER_ERROR());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		else {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getROLE_MODULE_MAPPING_ALREADY_EXISTS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public RoleModuleMappingApiResponse getAllRoleModuleMappings() {
		
		RoleModuleMappingApiResponse roleModuleMappingApiResponse = new RoleModuleMappingApiResponse();
		try {
			log.debug("Fetching all the Role Module mappings from the database");
			List<RoleModuleMapping> roleModuleMappingList = roleModuleMappingRepository.findAll();
			if(roleModuleMappingList.isEmpty()) {
				log.warn("No Module available");
			}
			else {
				log.debug("The displayed details:\n" + roleModuleMappingList);
			}
			log.info("The method getAllModule has been ended");
			List<RoleModuleMappingModel> roleModuleMappingDtoList = new ArrayList<>();
			for(RoleModuleMapping roleModuleMapping : roleModuleMappingList) {
				RoleModuleMappingModel roleModuleMappingModel= new RoleModuleMappingModel();
				BeanUtils.copyProperties(roleModuleMapping, roleModuleMappingModel);
				roleModuleMappingDtoList.add(roleModuleMappingModel);
				
			}
			roleModuleMappingApiResponse.setMessage(messageProperties.getSUCCESS());
			roleModuleMappingApiResponse.setStatus(Boolean.TRUE);
			roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			roleModuleMappingApiResponse.setRoleModuleMappingModelList(roleModuleMappingDtoList);
		}
		catch (Exception e) {
			roleModuleMappingApiResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			roleModuleMappingApiResponse.setStatus(Boolean.FALSE);
			roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_500());
		}
		
		return roleModuleMappingApiResponse;
	}

	@Override
	public ResponseEntity<ApiReturnResponse> updateRoleMapping(int id, RoleModuleMappingModel roleModuleMappingModel) {
		log.info("Entered into updateRoleMapping method");
		log.debug("Updating Role Module Mapping with id {} with updates {} ",id ,roleModuleMappingModel);
		if(roleModuleMappingRepository.getRoleIdAndModuleId(roleModuleMappingModel.getModuleId(),roleModuleMappingModel.getRoleId()).isEmpty()) {
		try {
			Optional<RoleModuleMapping> optionalRole = roleModuleMappingRepository.findById(id);
			if(optionalRole.isPresent()) {
				RoleModuleMapping roleModuleMapping = optionalRole.get();
//				roleModuleMapping.setRoleId(roleModuleMappingModel.getRoleId());
//				roleModuleMapping.setModuleId(roleModuleMappingModel.getModuleId());
				roleModuleMapping.setCreatee(roleModuleMappingModel.isCreatee());
				roleModuleMapping.setReead(roleModuleMappingModel.isReead());
				roleModuleMapping.setUpdatee(roleModuleMappingModel.isUpdatee());
				roleModuleMapping.setDeletee(roleModuleMappingModel.isDeletee());
				roleModuleMappingRepository.save(roleModuleMapping);
				log.debug("Updated Role Module Mapping {} in the database ",roleModuleMapping);
				apiReturnResponse.setStatus(Boolean.TRUE);
				apiReturnResponse.setMessage(messageProperties.getSUCCESS());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
			}
			else {
				log.warn("Role Module Mapping with id {} not found in the database ",id);
				apiReturnResponse.setStatus(Boolean.FALSE);
				apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role Module Mapping is not updated");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		else {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getROLE_MODULE_MAPPING_ALREADY_EXISTS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<ApiReturnResponse> deleteRoleMapping(int id) {
		log.info("Entered into deleteRoleMapping method");
		log.debug("Deleting Role Module Mapping with id {} from the database ", id);
		Optional<RoleModuleMapping> temp = roleModuleMappingRepository.findById(id);
		try {
			if(temp.isEmpty()) {
				apiReturnResponse.setStatus(Boolean.FALSE);
				apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
			}
			else {
			roleModuleMappingRepository.deleteById(id);
			log.debug("Deleted the Role Module Mapping with id {} from the database ",id);
			apiReturnResponse.setStatus(Boolean.TRUE);
			apiReturnResponse.setMessage(messageProperties.getSUCCESS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role Module Mapping is not deleted");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public RoleModuleMappingApiResponse getByRoleId(int id) {
		RoleModuleMappingApiResponse roleModuleMappingApiResponse = new RoleModuleMappingApiResponse();
		try {
			log.debug("Fetching the Role Module mappings with Role id {} from the database", id);
			List<RoleModuleMapping> optionalRoleModuleMapping = roleModuleMappingRepository.getByRoleId(id);
			if(optionalRoleModuleMapping.isEmpty()) {
				log.warn("No Module available");
				roleModuleMappingApiResponse.setMessage(messageProperties.getNOT_FOUND());
				roleModuleMappingApiResponse.setStatus(Boolean.FALSE);
				roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return roleModuleMappingApiResponse;
			}
			else {
				log.debug("The displayed details:\n" + optionalRoleModuleMapping);
			}
			log.info("The method getAllModule has been ended");
			List<RoleModuleMappingModel> roleModuleMappingDtoList = new ArrayList<>();
			for(RoleModuleMapping roleModuleMapping : optionalRoleModuleMapping) {
				RoleModuleMappingModel roleModuleMappingModel= new RoleModuleMappingModel();
				BeanUtils.copyProperties(roleModuleMapping, roleModuleMappingModel);
				roleModuleMappingDtoList.add(roleModuleMappingModel);
			}
			roleModuleMappingApiResponse.setMessage(messageProperties.getSUCCESS());
			roleModuleMappingApiResponse.setStatus(Boolean.TRUE);
			roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			roleModuleMappingApiResponse.setRoleModuleMappingModelList(roleModuleMappingDtoList);
		}
		catch (Exception e) {
			roleModuleMappingApiResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			roleModuleMappingApiResponse.setStatus(Boolean.FALSE);
			roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_500());
		}
		return roleModuleMappingApiResponse;
	}

	@Override
	public RoleModuleMappingApiResponse getBymoduleId(int id) {
		RoleModuleMappingApiResponse roleModuleMappingApiResponse = new RoleModuleMappingApiResponse();
		try {
			log.debug("Fetching all the Role Module mappings with Module id {} from the database",id);
			List<RoleModuleMapping> optionalRoleModuleMapping = roleModuleMappingRepository.getByModuleId(id);
			if(optionalRoleModuleMapping.isEmpty()) {
				log.warn("No Module available");
				roleModuleMappingApiResponse.setMessage(messageProperties.getNOT_FOUND());
				roleModuleMappingApiResponse.setStatus(Boolean.FALSE);
				roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return roleModuleMappingApiResponse;
			}
			else {
				log.debug("The displayed details:\n" + optionalRoleModuleMapping);
			}
			log.info("The method getAllModule has been ended");
			List<RoleModuleMappingModel> roleModuleMappingDtoList = new ArrayList<>();
			for(RoleModuleMapping roleModuleMapping : optionalRoleModuleMapping) {
				RoleModuleMappingModel roleModuleMappingModel= new RoleModuleMappingModel();
				BeanUtils.copyProperties(roleModuleMapping, roleModuleMappingModel);
				roleModuleMappingDtoList.add(roleModuleMappingModel);
			}
			roleModuleMappingApiResponse.setMessage(messageProperties.getSUCCESS());
			roleModuleMappingApiResponse.setStatus(Boolean.TRUE);
			roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			roleModuleMappingApiResponse.setRoleModuleMappingModelList(roleModuleMappingDtoList);
		}
		catch (Exception e) {
			roleModuleMappingApiResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			roleModuleMappingApiResponse.setStatus(Boolean.FALSE);
			roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_500());
		}
		return roleModuleMappingApiResponse;
	}

	@Override
	public ResponseEntity<ApiReturnResponse> update(int id, Map<String, Object> update) {
		log.info("Entered into update method");
		log.debug("Updating Role Module Mapping with id {} with updates {} ",id ,update);
		try {
		Optional<RoleModuleMapping> oldModule = roleModuleMappingRepository.findById(id);
		if(oldModule.isPresent()) {
			RoleModuleMapping newModule = oldModule.get();	
			update.forEach((field, value) -> {
				switch (field) {
				case "roleId":
					Role role = new Role();
					role.setId((int) value);
					if(roleModuleMappingRepository.getRoleIdAndModuleId(newModule.getModuleId().getId(),role.getId()).isEmpty()) {
						newModule.setRoleId(role);
					}
					else {
                		apiReturnResponse.setMessage(messageProperties.getROLE_MODULE_MAPPING_ALREADY_EXISTS());
            			throw new IllegalArgumentException();
                	}
					break;
				case "moduleId":
					Module module = new Module();
					module.setId((int) value);
					if(roleModuleMappingRepository.getRoleIdAndModuleId(module.getId(),newModule.getRoleId().getId()).isEmpty()) {
						newModule.setModuleId(module);
					}
					else {
                		apiReturnResponse.setMessage(messageProperties.getROLE_MODULE_MAPPING_ALREADY_EXISTS());
            			throw new IllegalArgumentException();
                	}
					break;
				case "createe":
					newModule.setCreatee((Boolean) value);
					break;
				case "reead":
					newModule.setReead((Boolean) value);
					break;
				case "updatee":
					newModule.setUpdatee((Boolean) value);
					break;
				case "deletee":
					newModule.setDeletee((Boolean) value);
					break;
				default:
					apiReturnResponse.setMessage("Invalid field: " + field);
					throw new IllegalArgumentException();
				}
			});
			log.debug("Updated Role Module Mapping {} in the database", newModule);
			roleModuleMappingRepository.save(newModule);
			apiReturnResponse.setStatus(Boolean.TRUE);
			apiReturnResponse.setMessage(messageProperties.getSUCCESS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
		} else {
			log.error("Module with id {} not found in the database", id);
			apiReturnResponse.setStatus(Boolean.TRUE);
			apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
		}
		}
		catch(IllegalArgumentException e) {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e) {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
