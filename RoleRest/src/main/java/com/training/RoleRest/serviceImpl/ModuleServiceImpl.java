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
import com.training.RoleRest.Response.ModuleApiResponse;
import com.training.RoleRest.entity.Module;
import com.training.RoleRest.model.ModuleModel;
import com.training.RoleRest.repository.ModuleRepository;
import com.training.RoleRest.service.ModuleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository moduleRepository;
	
	
	ApiReturnResponse apiReturnResponse = new ApiReturnResponse();
	
	@Autowired
	MessageProperties messageProperties;
	
	@Override
	public ResponseEntity<ApiReturnResponse> createModule(ModuleModel moduleModel) {
		log.info("Entered into createModule method");
		if(moduleRepository.findByModuleName(moduleModel.getModuleName()).isEmpty()) {
		try {
			Module module = new Module();
			BeanUtils.copyProperties(moduleModel, module);
			moduleRepository.save(module);
			log.debug("Saving Module{} data to the database ",module);
			apiReturnResponse.setStatus(Boolean.TRUE);
			apiReturnResponse.setMessage(messageProperties.getSUCCESS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Module is not created");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getERROR_CODE_500());
			apiReturnResponse.setStatusCode(messageProperties.getINERNAL_SERVER_ERROR());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		}
		else {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getMODULE_ALREADY_EXISTS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ModuleApiResponse getAllModule() {
		ModuleApiResponse moduleApiResponse = new ModuleApiResponse();
		try {
			log.debug("Fetching all the modules from the database");
			List<Module> moduleList = moduleRepository.findAll();
			if(moduleList.isEmpty()) {
				log.warn("No Module available");
			}
			else {
				log.debug("The displayed details:\n" + moduleList);
			}
			log.info("The method getAllModule has been ended");
			
			List<ModuleModel> moduleModelList = new ArrayList<>();
			for(Module module : moduleList) {
				ModuleModel moduleModel = new ModuleModel();
				BeanUtils.copyProperties(module, moduleModel);
				moduleModelList.add(moduleModel);
			}
			moduleApiResponse.setMessage(messageProperties.getSUCCESS());
			moduleApiResponse.setStatus(Boolean.TRUE);
			moduleApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			moduleApiResponse.setModuleModelList(moduleModelList);
		}
		catch (Exception e) {
			moduleApiResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			moduleApiResponse.setStatus(Boolean.FALSE);
			moduleApiResponse.setStatusCode(messageProperties.getERROR_CODE_500());
		}
		return moduleApiResponse;
	}

	@Override
	public ModuleApiResponse getModuleByName(String moduleName) {
		ModuleApiResponse moduleApiResponse = new ModuleApiResponse();
		try {
			log.debug("Fetching module with name {} from the database ", moduleName);
			Optional<Module> optionalModule = moduleRepository.findByModuleName(moduleName);
			Module module = optionalModule.get();
			if(optionalModule.isEmpty()) {
				log.warn("No Module available");
			}
			else {
				log.debug("The displayed details:\n" + module);
			}
			log.info("The method getAllModule has been ended");
			List<ModuleModel> moduleModelList = new ArrayList<>();
			ModuleModel moduleModel = new ModuleModel();
			BeanUtils.copyProperties(module, moduleModel);
			moduleModelList.add(moduleModel);
			moduleApiResponse.setMessage(messageProperties.getSUCCESS());
			moduleApiResponse.setStatus(Boolean.TRUE);
			moduleApiResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			moduleApiResponse.setModuleModelList(moduleModelList);
		}
		catch (Exception e) {
			moduleApiResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			moduleApiResponse.setStatus(Boolean.FALSE);
			moduleApiResponse.setStatusCode(messageProperties.getERROR_CODE_500());
		}
		return moduleApiResponse;
	}

	@Override
	public ResponseEntity<ApiReturnResponse> updateModule(int id, ModuleModel moduleModel) {
		log.info("Entered into updateModule method");
		log.debug("Updating module with id {} with updates {} ",id ,moduleModel);
		if(moduleRepository.findByModuleName(moduleModel.getModuleName()).isEmpty()) {
		try {
			Optional<Module> optionalRole = moduleRepository.findById(id);
			if(optionalRole.isPresent()) {
				Module module = optionalRole.get();
				BeanUtils.copyProperties(moduleModel, module);
				moduleRepository.save(module);
				log.debug("Updated module {} in the database ",module);
				apiReturnResponse.setStatus(Boolean.TRUE);
				apiReturnResponse.setMessage(messageProperties.getSUCCESS());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
			}
			else {
				log.warn("Module with id {} not found in the database ",id);
				apiReturnResponse.setStatus(Boolean.FALSE);
				apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Module is not updated");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		else {
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getMODULE_ALREADY_EXISTS());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<ApiReturnResponse> deleteModule(String moduleName) {
		log.info("Entered into deleteModule method");
		log.debug("Deleting role with name {} from the database ", moduleName);
		Optional<Module> temp = moduleRepository.findByModuleName(moduleName);
		try {
			if(temp.isEmpty()) {
				apiReturnResponse.setStatus(Boolean.FALSE);
				apiReturnResponse.setMessage(messageProperties.getNOT_FOUND());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.NOT_ACCEPTABLE);
			}else {
				Module m3 = temp.get();
				moduleRepository.deleteById(m3.getId());
				apiReturnResponse.setStatus(Boolean.TRUE);
				apiReturnResponse.setMessage(messageProperties.getSUCCESS());
				apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Module is not deleted");
			apiReturnResponse.setStatus(Boolean.FALSE);
			apiReturnResponse.setMessage(messageProperties.getINERNAL_SERVER_ERROR());
			apiReturnResponse.setStatusCode(messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<ApiReturnResponse> update(String name, Map<String, Object> update) {
		log.info("Entered into updateModule method");
		log.debug("Updating module with name {} with updates {} ",name ,update);
		try {
		Optional<Module> oldModule = moduleRepository.findByModuleName(name);
		if(oldModule.isPresent()) {
			Module newModule = oldModule.get();
			update.forEach((field, value) -> {
				switch (field) {
				case "moduleName":
					if(moduleRepository.findByModuleName((String) value).isEmpty()) {
						newModule.setModuleName((String) value);
					}
					else {
                		apiReturnResponse.setMessage(messageProperties.getMODULE_ALREADY_EXISTS());
            			throw new IllegalArgumentException();
                	}
					break;
				case "description":
					newModule.setDescription((String) value);
					break;
				case "isActive":
					newModule.setActive((Boolean) value);
					break;
				default:
					apiReturnResponse.setMessage("Invalid field: " + field);
					throw new IllegalArgumentException();
				}
			});
			log.debug("Updated module {} in the database", newModule);
			moduleRepository.save(newModule);
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