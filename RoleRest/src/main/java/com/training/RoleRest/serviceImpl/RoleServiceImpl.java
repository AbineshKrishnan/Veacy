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
import com.training.RoleRest.Response.RoleApiResponse;
import com.training.RoleRest.entity.Role;
import com.training.RoleRest.model.RoleModel;
import com.training.RoleRest.repository.RoleRepository;
import com.training.RoleRest.response.RoleEntityApiResponse;
import com.training.RoleRest.service.RoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * The RoleServiceImpl class provides an implementation of the RoleService interface,
 * handling CRUD operations and other actions on Role entities.
 */

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	ApiReturnResponse apiReturnResponse = new ApiReturnResponse();
	
	@Autowired
	MessageProperties messageProperties;
	
	/**
     * {@inheritDoc}
     */
	
	@Override
	public ResponseEntity<ApiReturnResponse> createRole(RoleModel roleModel) {
		log.info("Entered into createRole method");
		if(roleRepository.findByName(roleModel.getName()).isEmpty()) {
        try {
        	Role role = new Role();
        	BeanUtils.copyProperties(roleModel, role);
        	roleRepository.save(role);
        	log.debug("Saving Role {} data to the database ",role);
        	apiReturnResponse.setStatus(Boolean.TRUE);
			apiReturnResponse.setMessage(messageProperties.getSUCCESS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
        }
        catch(Exception e) {
        	log.warn("Role is not created");
        	apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getERROR_CODE_500());
			apiReturnResponse.setStatusCode(messageProperties.getINERNAL_SERVER_ERROR());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
		}
		else {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getROLE_ALREADY_EXISTS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
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
			if(roleList.isEmpty()) {
				log.warn("No Module available");
				response.setMessage(messageProperties.getNOT_FOUND());
				response.setStatus(Boolean.FALSE);
				response.setStatusCode(messageProperties.getERROR_CODE_200());
				return response;
			}
			else {
				log.debug("The displayed details:\n" + roleList);
			}
			log.info("The method getAllModule has been ended");
			
			List<RoleEntityApiResponse> roleModelList = new ArrayList<>();
			for(Role role : roleList) {
				RoleEntityApiResponse roleModel = new RoleEntityApiResponse();
				BeanUtils.copyProperties(role, roleModel);
				roleModelList.add(roleModel);
			}
			response.setMessage(messageProperties.getSUCCESS());
			response.setStatus(Boolean.TRUE);
			response.setStatusCode(messageProperties.getERROR_CODE_200());
			response.setRoleEntityApiResponseList(roleModelList);
		} catch (Exception e) {
			response.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			response.setStatus(Boolean.FALSE);
			response.setStatusCode(messageProperties.getERROR_CODE_500());
		}
		
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
			if(optionalRole.isEmpty()) {
				log.warn("No Role available");
				roleApiResponse.setMessage(messageProperties.getNOT_FOUND());
				roleApiResponse.setStatus(Boolean.FALSE);
				roleApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return roleApiResponse;
			}
			else {
				log.debug("The displayed details:\n" + role);
			}
			log.info("The method getAllModule has been ended");
			List<RoleEntityApiResponse> roleModelList = new ArrayList<>();
			RoleEntityApiResponse roleModel = new RoleEntityApiResponse();
			BeanUtils.copyProperties(role, roleModel);
			roleModelList.add(roleModel);
			roleApiResponse.setMessage(messageProperties.getSUCCESS());
			roleApiResponse.setStatus(Boolean.TRUE);
			roleApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			roleApiResponse.setRoleEntityApiResponseList(roleModelList);
		}
		catch (Exception e) {
			roleApiResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			roleApiResponse.setStatus(Boolean.FALSE);
			roleApiResponse.setStatusCode(messageProperties.getERROR_CODE_500());
		}
		return roleApiResponse;
	}

	/**
     * {@inheritDoc}
     */
	
	@Override
	public ResponseEntity<ApiReturnResponse> updateRole(String name, RoleModel roleModel) {
		log.info("Entered into updateRole method");
		log.debug("Updating module with name {} with updates {} ",name ,roleModel);
		if(roleRepository.findByName(roleModel.getName()).isEmpty()) {
		try {
			Optional<Role> optionalRole = roleRepository.findByName(name);
			if(optionalRole.isPresent()) {
				log.info("inside role");
				Role role = optionalRole.get();
				log.info("id founding " +role.getName());
				BeanUtils.copyProperties(roleModel, role);
				log.info("model"+role);
				roleRepository.save(role);
				log.debug("Updated module {} in the database ",role);
				apiReturnResponse.setStatus(Boolean.TRUE);
				apiReturnResponse.setMessage(messageProperties.getSUCCESS());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
			}
			else {
				log.warn("Role with name {} not found in the database ",name);
				apiReturnResponse.setStatus(Boolean.FALSE);
				apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role is not updated");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		else {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getROLE_ALREADY_EXISTS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
		}
	}

	/**
     * {@inheritDoc}
     */
	
	@Override
	public ResponseEntity<ApiReturnResponse> deleteRole(String name) {
		log.info("Entered into deleteRole method");
		log.debug("Deleting role with id {} from the database ", name);
		Optional<Role> temp =  roleRepository.findByName(name);
		try {
			if(temp.isEmpty()) {
				apiReturnResponse.setStatus(Boolean.FALSE);
				apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
			}
			else {
				Role r3 = temp.get();
				roleRepository.deleteById(r3.getId());
				log.debug("Deleted the Role with name {} from the database ",name);
				apiReturnResponse.setStatus(Boolean.TRUE);
				apiReturnResponse.setMessage(messageProperties.getSUCCESS());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role is not deleted");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
     * {@inheritDoc}
     */
	
	@Override
	public ResponseEntity<ApiReturnResponse> update(String name, Map<String, Object> updates) {
		log.info("Entered into updateRole method");
		log.debug("Updating module with name {} with updates {} ",name ,updates);
		try {
        Optional<Role> existingRole = roleRepository.findByName(name);
        if (existingRole.isPresent()) {
        	Role updatedRole = existingRole.get();
            updates.forEach((field, value) -> {
                switch (field) {
                    case "name":
                    	if(roleRepository.findByName((String) value).isEmpty()) {
                    		updatedRole.setName((String) value);
                    	}
                    	else {
                    		apiReturnResponse.setMessage(messageProperties.getROLE_ALREADY_EXISTS());
                			throw new IllegalArgumentException();
                    	}
                    	break;
                    case "description":
                    	updatedRole.setDescription((String) value);
                        break;
                    case "isInvitee":
                    	updatedRole.setInvitee((Boolean) value);
                        break;
                    case "isActive":
                    	updatedRole.setActive((Boolean) value);
                        break;
                    default:
                    	apiReturnResponse.setMessage("Invalid field: " + field);
                        throw new IllegalArgumentException();
                }
            });
            roleRepository.save(updatedRole);
            log.debug("Updated module {} in the database", updatedRole);
            apiReturnResponse.setStatus(Boolean.TRUE);
			apiReturnResponse.setMessage(messageProperties.getSUCCESS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
        } else {
        	log.error("Module with name {} not found in the database", name);
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
