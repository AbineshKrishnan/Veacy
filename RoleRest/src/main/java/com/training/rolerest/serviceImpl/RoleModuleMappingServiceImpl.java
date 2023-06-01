/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.rolerest.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.rolerest.apiresponse.ApiReturnResponse;
import com.training.rolerest.apiresponse.MessageProperties;
import com.training.rolerest.apiresponse.RoleModuleMappingApiResponse;
import com.training.rolerest.entity.Module;
import com.training.rolerest.entity.Role;
import com.training.rolerest.entity.RoleModuleMapping;
import com.training.rolerest.entityresponse.RoleModuleMappingEntityApiResponse;
import com.training.rolerest.model.RoleModuleMappingModel;
import com.training.rolerest.repository.ModuleRepository;
import com.training.rolerest.repository.RoleModuleMappingRepository;
import com.training.rolerest.repository.RoleRepository;
import com.training.rolerest.service.RoleModuleMappingService;
import com.training.rolerest.support.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * The RoleModuleMappingServiceImpl class provides an implementation of the
 * RoleModuleMappingService interface, handling CRUD operations and other
 * actions on RoleModuleMapping entities.
 */

@Service
@Slf4j
public class RoleModuleMappingServiceImpl implements RoleModuleMappingService {

	@Autowired
	RoleModuleMappingRepository roleModuleMappingRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ModuleRepository moduleRepository;

	ApiReturnResponse apiReturnResponse = new ApiReturnResponse();

	@Autowired
	MessageProperties messageProperties;

	@Autowired
	Util util;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> createRoleMapping(RoleModuleMappingModel roleModuleMappingModel) {
		log.info("Entered into createRoleMapping method");
		try {
			if (roleRepository.findById(roleModuleMappingModel.getRoleId()).isEmpty()) {
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.BAD_REQUEST);
			} else if (moduleRepository.findById(roleModuleMappingModel.getModuleId()).isEmpty()) {
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.BAD_REQUEST);
			} else {
				if (roleModuleMappingRepository
						.getRoleIdAndModuleId(roleModuleMappingModel.getModuleId(), roleModuleMappingModel.getRoleId())
						.isEmpty()) {

					RoleModuleMapping roleModuleMapping = new RoleModuleMapping();
					BeanUtils.copyProperties(roleModuleMappingModel, roleModuleMapping);
					Role role = new Role();
					Module module = new Module();
					role.setId(roleModuleMappingModel.getRoleId());
					module.setId(roleModuleMappingModel.getModuleId());
					roleModuleMapping.setRoleId(role);
					roleModuleMapping.setModuleId(module);
					roleModuleMappingRepository.save(roleModuleMapping);
					log.debug("Saving RoleModuleMapping {} data to the database ", roleModuleMappingModel);
					apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
							messageProperties.getERROR_CODE_200());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);

				} else {
					apiReturnResponse = util.setApiReturnResponseMessage(
							messageProperties.getROLE_MODULE_MAPPING_ALREADY_EXISTS(), Boolean.FALSE,
							messageProperties.getERROR_CODE_200());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Role is not created");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(),
					Boolean.FALSE, messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public RoleModuleMappingApiResponse getAllRoleModuleMappings() {

		RoleModuleMappingApiResponse roleModuleMappingApiResponse = new RoleModuleMappingApiResponse();
		try {
			log.debug("Fetching all the Role Module mappings from the database");
			List<RoleModuleMapping> roleModuleMappingList = roleModuleMappingRepository.findAll();
			if (roleModuleMappingList.isEmpty()) {
				log.warn("No Module available");
				roleModuleMappingApiResponse = util.setRoleModuleMappingApiResponseMessage(
						messageProperties.getNOT_FOUND(), Boolean.FALSE, messageProperties.getERROR_CODE_200());
				return roleModuleMappingApiResponse;
			}
			log.info("The method getAllModule has been ended");
			List<RoleModuleMappingEntityApiResponse> roleModuleMappingDtoList = new ArrayList<>();
			for (RoleModuleMapping roleModuleMapping : roleModuleMappingList) {
				RoleModuleMappingEntityApiResponse roleModuleMappingModel = new RoleModuleMappingEntityApiResponse();
				roleModuleMappingModel.setRole(roleModuleMapping.getRoleId().getName());
				roleModuleMappingModel.setModule(roleModuleMapping.getModuleId().getModuleName());
				BeanUtils.copyProperties(roleModuleMapping, roleModuleMappingModel);
				roleModuleMappingDtoList.add(roleModuleMappingModel);
			}
			roleModuleMappingApiResponse = util.setRoleModuleMappingApiResponseMessage(messageProperties.getSUCCESS(),
					Boolean.TRUE, messageProperties.getERROR_CODE_200());
			roleModuleMappingApiResponse.setRoleModuleMappingEntityApiResponseList(roleModuleMappingDtoList);
		} catch (Exception e) {
			util.setRoleModuleMappingApiResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(), Boolean.FALSE,
					messageProperties.getERROR_CODE_500());
		}

		return roleModuleMappingApiResponse;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> deleteRoleMapping(int id) {
		log.info("Entered into deleteRoleMapping method");
		log.debug("Deleting Role Module Mapping with id {} from the database ", id);
		try {
			Optional<RoleModuleMapping> temp = roleModuleMappingRepository.findById(id);
			if (temp.isEmpty()) {
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				roleModuleMappingRepository.deleteById(id);
				log.debug("Deleted the Role Module Mapping with id {} from the database ", id);
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Role Module Mapping is not deleted");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(),
					Boolean.FALSE, messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public RoleModuleMappingApiResponse getByRoleId(int id) {
		RoleModuleMappingApiResponse roleModuleMappingApiResponse = new RoleModuleMappingApiResponse();
		try {
			log.debug("Fetching the Role Module mappings with Role id {} from the database", id);
			List<RoleModuleMapping> optionalRoleModuleMapping = roleModuleMappingRepository.getByRoleId(id);
			if (optionalRoleModuleMapping.isEmpty()) {
				log.warn("No Module available");
				roleModuleMappingApiResponse.setMessage(messageProperties.getNOT_FOUND());
				roleModuleMappingApiResponse.setStatus(Boolean.FALSE);
				roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return roleModuleMappingApiResponse;
			} else {
				log.debug("The displayed details:\n" + optionalRoleModuleMapping);
			}
			log.info("The method getAllModule has been ended");
			List<RoleModuleMappingEntityApiResponse> roleModuleMappingDtoList = new ArrayList<>();
			for (RoleModuleMapping roleModuleMapping : optionalRoleModuleMapping) {
				RoleModuleMappingEntityApiResponse roleModuleMappingModel = new RoleModuleMappingEntityApiResponse();
				roleModuleMappingModel.setRole(roleModuleMapping.getRoleId().getName());
				roleModuleMappingModel.setModule(roleModuleMapping.getModuleId().getModuleName());
				BeanUtils.copyProperties(roleModuleMapping, roleModuleMappingModel);
				roleModuleMappingDtoList.add(roleModuleMappingModel);
			}
			roleModuleMappingApiResponse = util.setRoleModuleMappingApiResponseMessage(messageProperties.getSUCCESS(),
					Boolean.TRUE, messageProperties.getERROR_CODE_200());
			roleModuleMappingApiResponse.setRoleModuleMappingEntityApiResponseList(roleModuleMappingDtoList);
		} catch (Exception e) {
			util.setRoleModuleMappingApiResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(), Boolean.FALSE,
					messageProperties.getERROR_CODE_500());
		}
		return roleModuleMappingApiResponse;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public RoleModuleMappingApiResponse getBymoduleId(int id) {
		RoleModuleMappingApiResponse roleModuleMappingApiResponse = new RoleModuleMappingApiResponse();
		try {
			log.debug("Fetching all the Role Module mappings with Module id {} from the database", id);
			List<RoleModuleMapping> optionalRoleModuleMapping = roleModuleMappingRepository.getByModuleId(id);
			if (optionalRoleModuleMapping.isEmpty()) {
				log.warn("No Module available");
				roleModuleMappingApiResponse.setMessage(messageProperties.getNOT_FOUND());
				roleModuleMappingApiResponse.setStatus(Boolean.FALSE);
				roleModuleMappingApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return roleModuleMappingApiResponse;
			} else {
				log.debug("The displayed details:\n" + optionalRoleModuleMapping);
			}
			log.info("The method getAllModule has been ended");
			List<RoleModuleMappingEntityApiResponse> roleModuleMappingDtoList = new ArrayList<>();
			for (RoleModuleMapping roleModuleMapping : optionalRoleModuleMapping) {
				RoleModuleMappingEntityApiResponse roleModuleMappingModel = new RoleModuleMappingEntityApiResponse();
				roleModuleMappingModel.setRole(roleModuleMapping.getRoleId().getName());
				roleModuleMappingModel.setModule(roleModuleMapping.getModuleId().getModuleName());
				BeanUtils.copyProperties(roleModuleMapping, roleModuleMappingModel);
				roleModuleMappingDtoList.add(roleModuleMappingModel);
			}
			roleModuleMappingApiResponse = util.setRoleModuleMappingApiResponseMessage(messageProperties.getSUCCESS(),
					Boolean.TRUE, messageProperties.getERROR_CODE_200());
			roleModuleMappingApiResponse.setRoleModuleMappingEntityApiResponseList(roleModuleMappingDtoList);
		} catch (Exception e) {
			util.setRoleModuleMappingApiResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(), Boolean.FALSE,
					messageProperties.getERROR_CODE_500());
		}
		return roleModuleMappingApiResponse;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> update(int id, Map<String, Object> update) {
		log.info("Entered into update method");
		log.debug("Updating Role Module Mapping with id {} with updates {} ", id, update);
		try {
			Optional<RoleModuleMapping> oldModule = roleModuleMappingRepository.findById(id);
			if (oldModule.isPresent()) {
				RoleModuleMapping newModule = oldModule.get();
				update.forEach((field, value) -> {
					switch (field) {
					case "roleId":
						Role role = new Role();
						role.setId((int) value);
						if (roleRepository.findById(role.getId()).isEmpty()) {
							apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
							throw new IllegalArgumentException();
						} else {
							if (roleModuleMappingRepository
									.getRoleIdAndModuleId(newModule.getModuleId().getId(), role.getId()).isEmpty()) {
								newModule.setRoleId(role);
							} else {
								apiReturnResponse.setMessage(messageProperties.getROLE_MODULE_MAPPING_ALREADY_EXISTS());
								throw new IllegalArgumentException();
							}
						}

						break;
					case "moduleId":
						Module module = new Module();
						module.setId((int) value);
						if (moduleRepository.findById(module.getId()).isEmpty()) {
							apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
							throw new IllegalArgumentException();
						} else {
							if (roleModuleMappingRepository
									.getRoleIdAndModuleId(module.getId(), newModule.getRoleId().getId()).isEmpty()) {
								newModule.setModuleId(module);
							} else {
								apiReturnResponse.setMessage(messageProperties.getROLE_MODULE_MAPPING_ALREADY_EXISTS());
								throw new IllegalArgumentException();
							}
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
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			} else {
				log.error("Module with id {} not found in the database", id);
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNOT_FOUND(), Boolean.TRUE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			}
		} catch (IllegalArgumentException e) {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(),
					Boolean.FALSE, messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
