/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.serviceImpl;

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
import com.training.RoleRest.entity.Role;
import com.training.RoleRest.model.RoleModel;
import com.training.RoleRest.repository.RoleRepository;
import com.training.RoleRest.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	ApiReturnResponse apiReturnResponse = new ApiReturnResponse();
	
	@Autowired
	MessageProperties messageProperties;
	
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

	@Override
	public List<Role> getAllRole() {
		log.debug("Fetching all the roles from the database");
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		log.debug("Fetching role with name {} from the database ", name);
		return roleRepository.findByName(name);
	}

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

	@Override
	public ResponseEntity<ApiReturnResponse> update(String name, Map<String, Object> updates) {
		log.info("Entered into updateModule method");
		log.debug("Updating module with name {} with updates {} ",name ,updates);
        Optional<Role> existingRole = roleRepository.findByName(name);
        if (existingRole.isPresent()) {
        	Role updatedRole = existingRole.get();
            updates.forEach((field, value) -> {
                switch (field) {
                    case "name":
                    	updatedRole.setName((String) value);
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
                        throw new IllegalArgumentException("Invalid field: " + field);
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
}
