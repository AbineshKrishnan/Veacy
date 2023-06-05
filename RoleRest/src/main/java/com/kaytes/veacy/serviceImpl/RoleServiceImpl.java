/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kaytes.veacy.apiresponse.ApiReturnResponse;
import com.kaytes.veacy.apiresponse.MessageProperties;
import com.kaytes.veacy.apiresponse.RoleApiResponse;
import com.kaytes.veacy.entity.Role;
import com.kaytes.veacy.entityresponse.RoleEntityApiResponse;
import com.kaytes.veacy.exception.InvalidAttributeOrFieldException;
import com.kaytes.veacy.model.RoleModel;
import com.kaytes.veacy.repository.RoleRepository;
import com.kaytes.veacy.service.RoleService;
import com.kaytes.veacy.support.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * The RoleServiceImpl class provides an implementation of the RoleService
 * interface, handling CRUD operations and other actions on Role entities.
 */

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	ApiReturnResponse apiReturnResponse = new ApiReturnResponse();

	@Autowired
	MessageProperties messageProperties;

	@Autowired
	Util util ;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> createRole(RoleModel roleModel) {
		log.info("Entered into createRole method");

		if (!(roleModel.getName().isEmpty() || roleModel.getDescription().isEmpty())) {
			try {
				if (roleRepository.findByName(roleModel.getName()).isEmpty()) {
					log.info("Validated the input data");
					Role role = new Role();
					BeanUtils.copyProperties(roleModel, role);
					roleRepository.save(role);
					log.debug("Saving Role {} data to the database ", role);
					apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
							messageProperties.getERROR_CODE_200());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);

				} else {
					log.warn("Role already exists");
					apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getROLE_ALREADY_EXISTS(),
							Boolean.FALSE, messageProperties.getERROR_CODE_200());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
				}
			} catch (Exception e) {
				log.error("Role is not created");
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(),
						Boolean.FALSE, messageProperties.getERROR_CODE_500());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			log.warn("Invalid Attribute or Field Exception");
			throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
		}

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public RoleApiResponse getAllRole() {

		RoleApiResponse response = new RoleApiResponse();
		try {
			log.debug("Fetching all the roles from the database");
			List<Role> roleList = roleRepository.findAll();
			if (roleList.isEmpty()) {
				log.warn("No Module available");
				response = util.setRoleApiResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return response;
			}
			log.debug("The displayed details:\n" + roleList);
			List<RoleEntityApiResponse> roleEntityApiResponseList = new ArrayList<>();
			for (Role role : roleList) {
				RoleEntityApiResponse roleModel = new RoleEntityApiResponse();
				BeanUtils.copyProperties(role, roleModel);
				roleEntityApiResponseList.add(roleModel);
			}
			log.info("Fetched the data from the data base");
			response = util.setRoleApiResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
					messageProperties.getERROR_CODE_200());
			response.setRoleModelList(roleEntityApiResponseList);
		} catch (Exception e) {
			util.setRoleApiResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(), Boolean.FALSE,
					messageProperties.getERROR_CODE_500());
		}
		log.info("The method getAllModule has been ended");
		return response;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public RoleApiResponse getRoleByName(String name) {
		RoleApiResponse roleApiResponse = new RoleApiResponse();
		try {
			log.debug("Fetching role with name {} from the database ", name);
			Optional<Role> optionalRole = roleRepository.findByName(name);
			Role role = optionalRole.get();
			if (optionalRole.isEmpty()) {
				log.warn("No Role available");
				roleApiResponse = util.setRoleApiResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return roleApiResponse;
			} else {
				log.debug("The displayed details:\n" + role);
			}
			List<RoleEntityApiResponse> roleEntityApiResponseList = new ArrayList<>();
			RoleEntityApiResponse roleModel = new RoleEntityApiResponse();
			BeanUtils.copyProperties(role, roleModel);
			roleEntityApiResponseList.add(roleModel);
			roleApiResponse = util.setRoleApiResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
					messageProperties.getERROR_CODE_200());
			roleApiResponse.setRoleModelList(roleEntityApiResponseList);
		} catch (Exception e) {
			log.error("Can't get the Role");
			util.setRoleApiResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(), Boolean.FALSE,
					messageProperties.getERROR_CODE_500());
		}
		log.info("The method getRoleByName has been ended");
		return roleApiResponse;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> deleteRole(String name) {
		log.info("Entered into deleteRole method");
		log.debug("Deleting role with id {} from the database ", name);
		try {
			Optional<Role> temp = roleRepository.findByName(name);
			if (temp.isEmpty()) {
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				Role r3 = temp.get();
				roleRepository.deleteById(r3.getId());
				log.debug("Deleted the Role with name {} from the database ", name);
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Role is not deleted");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(),
					Boolean.FALSE, messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> update(String name, Map<String, Object> updates) {
		log.info("Entered into updateRole method");
		log.debug("Updating module with name {} with updates {} ", name, updates);
		try {
			Optional<Role> existingRole = roleRepository.findByName(name);
			if (existingRole.isPresent()) {
				Role updatedRole = existingRole.get();
				updates.forEach((field, value) -> {
					switch (field) {
					case "name":
						if (value.toString().isEmpty()) {
							throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
						} else {
							if (roleRepository.findByName((String) value).isEmpty()) {
								updatedRole.setName((String) value);
							} else {
								apiReturnResponse.setMessage(messageProperties.getROLE_ALREADY_EXISTS());
								throw new IllegalArgumentException();
							}
						}
						break;
					case "description":
						if (value.toString().isEmpty()) {
							throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
						} else {
							updatedRole.setDescription((String) value);
						}
						break;
					case "isInvitee":
						if (value.toString().isEmpty()) {
							throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
						} else {
							updatedRole.setInvitee((Boolean) value);
						}
						break;
					case "isActive":
						if (value.toString().isEmpty()) {
							throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
						} else {
							updatedRole.setActive((Boolean) value);
						}
						break;
					default:
						apiReturnResponse.setMessage("Invalid field: " + field);
						throw new IllegalArgumentException();
					}
				});
				roleRepository.save(updatedRole);
				log.debug("Updated module {} in the database", updatedRole);
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			} else {
				log.error("Module with name {} not found in the database", name);
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
