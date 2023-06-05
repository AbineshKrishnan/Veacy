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
import com.kaytes.veacy.entity.Module;
import com.kaytes.veacy.apiresponse.ApiReturnResponse;
import com.kaytes.veacy.apiresponse.MessageProperties;
import com.kaytes.veacy.apiresponse.ModuleApiResponse;
import com.kaytes.veacy.entityresponse.ModuleEntityApiResponse;
import com.kaytes.veacy.exception.InvalidAttributeOrFieldException;
import com.kaytes.veacy.model.ModuleModel;
import com.kaytes.veacy.repository.ModuleRepository;
import com.kaytes.veacy.service.ModuleService;
import com.kaytes.veacy.support.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * The ModuleServiceImpl class provides an implementation of the ModuleService
 * interface, handling CRUD operations and other actions on Module entities.
 */

@Service
@Slf4j
public class ModuleServiceImpl implements ModuleService {

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
	public ResponseEntity<ApiReturnResponse> createModule(ModuleModel moduleModel) {
		log.info("Entered into createModule method");
		try {
			if (!(moduleModel.getModuleName().isEmpty() || moduleModel.getDescription().isEmpty())) {

				if (moduleRepository.findByModuleName(moduleModel.getModuleName()).isEmpty()) {
					Module module = new Module();
					BeanUtils.copyProperties(moduleModel, module);
					moduleRepository.save(module);
					log.debug("Saving Module{} data to the database ", module);
					apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
							messageProperties.getERROR_CODE_200());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
				} else {
					apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getMODULE_ALREADY_EXISTS(),
							Boolean.FALSE, messageProperties.getERROR_CODE_200());
					return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
				}

			} else {
				throw new InvalidAttributeOrFieldException("Invalid input Exception");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Module is not created");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(),
					Boolean.FALSE, messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ModuleApiResponse getAllModule() {
		ModuleApiResponse moduleApiResponse = new ModuleApiResponse();
		try {
			log.debug("Fetching all the modules from the database");
			List<Module> moduleList = moduleRepository.findAll();
			if (moduleList.isEmpty()) {
				log.warn("No Module available");
				moduleApiResponse = util.setModuleApiResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return moduleApiResponse;
			}

			List<ModuleEntityApiResponse> moduleEntityApiResponseList = new ArrayList<>();
			for (Module module : moduleList) {
				ModuleEntityApiResponse moduleModel = new ModuleEntityApiResponse();
				BeanUtils.copyProperties(module, moduleModel);
				moduleEntityApiResponseList.add(moduleModel);
			}
			moduleApiResponse = util.setModuleApiResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
					messageProperties.getERROR_CODE_200());
			moduleApiResponse.setModuleModelList(moduleEntityApiResponseList);
		} catch (Exception e) {
			util.setModuleApiResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(), Boolean.FALSE,
					messageProperties.getERROR_CODE_500());
		}
		return moduleApiResponse;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ModuleApiResponse getModuleByName(String moduleName) {
		ModuleApiResponse moduleApiResponse = new ModuleApiResponse();
		try {
			log.debug("Fetching module with name {} from the database ", moduleName);
			Optional<Module> optionalModule = moduleRepository.findByModuleName(moduleName);
			Module module = optionalModule.get();
			if (optionalModule.isEmpty()) {
				log.warn("No Module available");
				moduleApiResponse = util.setModuleApiResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return moduleApiResponse;
			} else {
				log.debug("The displayed details:\n" + module);
			}
			log.info("The method getAllModule has been ended");
			List<ModuleEntityApiResponse> moduleEntityApiResponseList = new ArrayList<>();
			ModuleEntityApiResponse moduleModel = new ModuleEntityApiResponse();
			BeanUtils.copyProperties(module, moduleModel);
			moduleEntityApiResponseList.add(moduleModel);
			moduleApiResponse = util.setModuleApiResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
					messageProperties.getERROR_CODE_200());
			moduleApiResponse.setModuleModelList(moduleEntityApiResponseList);
		} catch (Exception e) {
			util.setModuleApiResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(), Boolean.FALSE,
					messageProperties.getERROR_CODE_500());
		}
		return moduleApiResponse;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> deleteModule(String moduleName) {
		log.info("Entered into deleteModule method");
		log.debug("Deleting role with name {} from the database ", moduleName);
		try {
			Optional<Module> temp = moduleRepository.findByModuleName(moduleName);
			if (temp.isEmpty()) {
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getNOT_FOUND(), Boolean.FALSE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.NOT_ACCEPTABLE);
			} else {
				Module m3 = temp.get();
				moduleRepository.deleteById(m3.getId());
				apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getSUCCESS(), Boolean.TRUE,
						messageProperties.getERROR_CODE_200());
				return new ResponseEntity<>(apiReturnResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Module is not deleted");
			apiReturnResponse = util.setApiReturnResponseMessage(messageProperties.getINERNAL_SERVER_ERROR(),
					Boolean.FALSE, messageProperties.getERROR_CODE_500());
			return new ResponseEntity<>(apiReturnResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public ResponseEntity<ApiReturnResponse> update(String name, Map<String, Object> update) {
		log.info("Entered into updateModule method");
		log.debug("Updating module with name {} with updates {} ", name, update);
		try {
			Optional<Module> oldModule = moduleRepository.findByModuleName(name);
			if (oldModule.isPresent()) {
				Module newModule = oldModule.get();
				update.forEach((field, value) -> {
					switch (field) {
					case "moduleName":
						if (value.toString().isEmpty()) {
							throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
						} else {
							if (moduleRepository.findByModuleName((String) value).isEmpty()) {
								newModule.setModuleName((String) value);
							} else {
								apiReturnResponse.setMessage(messageProperties.getMODULE_ALREADY_EXISTS());
								throw new IllegalArgumentException();
							}
						}
						break;
					case "description":
						if (value.toString().isEmpty()) {
							throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
						} else {
							newModule.setDescription((String) value);
						}
						break;
					case "isActive":
						if (value.toString().isEmpty()) {
							throw new InvalidAttributeOrFieldException("Invalid Attribute or Field Exception");
						} else {
							newModule.setActive((Boolean) value);
						}
						break;
					default:
						apiReturnResponse.setMessage("Invalid field: " + field);
						throw new IllegalArgumentException();
					}
				});
				log.debug("Updated module {} in the database", newModule);
				moduleRepository.save(newModule);
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