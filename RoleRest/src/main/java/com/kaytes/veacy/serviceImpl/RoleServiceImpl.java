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

import com.kaytes.veacy.dto.ApiReturnResponse;
import com.kaytes.veacy.dto.RoleApiResponse;
import com.kaytes.veacy.entity.Role;
import com.kaytes.veacy.exception.InvalidAttributeOrFieldException;
import com.kaytes.veacy.model.RoleModel;
import com.kaytes.veacy.properties.MessageProperties;
import com.kaytes.veacy.repository.RoleRepository;
import com.kaytes.veacy.response.RoleResponse;
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
	Util util;

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> createRole(RoleModel roleModel) {
		log.info("Entered into createRole method");
		try {
			if (!(roleModel.getName().isEmpty() || roleModel.getDescription().isEmpty())) {
				if (roleRepository.findByName(roleModel.getName()).isEmpty()) {
					log.info("Validated the input data");
					Role role = new Role();
					BeanUtils.copyProperties(roleModel, role);
					roleRepository.save(role);
					log.debug("Saving Role {} data to the database ", role);
					apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
							messageProperties.getErrorCode200());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);

				} else {
					log.warn("Role already exists");
					apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getRoleAlreadyExists(),
							Boolean.FALSE, messageProperties.getErrorCode400());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				log.warn("Invalid Attribute or Field Exception");
				throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception123");
			}
		} 
		catch(NullPointerException e) {
			log.warn("Invalid Attribute");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getInvalidAttribute(),
							Boolean.FALSE, messageProperties.getErrorCode400());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(InvalidAttributeOrFieldException e) {
			log.warn("Invalid Value");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getInvalidInput(),
							Boolean.FALSE, messageProperties.getErrorCode400());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
			log.warn("Role is not created");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getInternalServerError(),
					Boolean.FALSE, messageProperties.getErrorCode500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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
				response = util.setRoleApiResponseMessage(messageProperties.getNotFound(), Boolean.FALSE,
						messageProperties.getErrorCode200());
				return response;
			}
			log.debug("The displayed details:\n" + roleList);
			List<RoleResponse> roleResponseList = new ArrayList<>();
			for (Role role : roleList) {
				RoleResponse roleResponse = new RoleResponse();
				BeanUtils.copyProperties(role, roleResponse);
				roleResponseList.add(roleResponse);
			}
			log.info("Fetched the data from the data base");
			response = util.setRoleApiResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
					messageProperties.getErrorCode200());
			response.setRoleModelList(roleResponseList);
		} catch (Exception e) {
			util.setRoleApiResponseMessage(messageProperties.getInternalServerError(), Boolean.FALSE,
					messageProperties.getErrorCode500());
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
				roleApiResponse = util.setRoleApiResponseMessage(messageProperties.getNotFound(), Boolean.FALSE,
						messageProperties.getErrorCode200());
				return roleApiResponse;
			} else {
				log.debug("The displayed details:\n" + role);
			}
			List<RoleResponse> roleResponseList = new ArrayList<>();
			RoleResponse roleResponse = new RoleResponse();
			BeanUtils.copyProperties(role, roleResponse);
			roleResponseList.add(roleResponse);
			roleApiResponse = util.setRoleApiResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
					messageProperties.getErrorCode200());
			roleApiResponse.setRoleModelList(roleResponseList);
		} catch (Exception e) {
			log.error("Can't get the Role");
			util.setRoleApiResponseMessage(messageProperties.getInternalServerError(), Boolean.FALSE,
					messageProperties.getErrorCode500());
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
			Optional<Role> optRole = roleRepository.findByName(name);
			if (optRole.isEmpty()) {
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNotFound(), Boolean.FALSE,
						messageProperties.getErrorCode200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				Role role = optRole.get();
				roleRepository.deleteById(role.getId());
				log.debug("Deleted the Role with name {} from the database ", name);
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
						messageProperties.getErrorCode200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Role is not deleted");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getInternalServerError(),
					Boolean.FALSE, messageProperties.getErrorCode500());
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
								apiReturnResponse.setMessage(messageProperties.getRoleAlreadyExists());
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
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSuccess(), Boolean.TRUE,
						messageProperties.getErrorCode200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			} else {
				log.error("Module with name {} not found in the database", name);
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNotFound(), Boolean.TRUE,
						messageProperties.getErrorCode400());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			}
		}
		catch(InvalidAttributeOrFieldException e) {
			log.warn("Invalid Value");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getInvalidInput(),
							Boolean.FALSE, messageProperties.getErrorCode400());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (IllegalArgumentException e) {
			log.warn("Invalid Attribute");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setStatusCode(messageProperties.getErrorCode400());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
		}
		catch (Exception e) {
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getInternalServerError(),
					Boolean.FALSE, messageProperties.getErrorCode500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
