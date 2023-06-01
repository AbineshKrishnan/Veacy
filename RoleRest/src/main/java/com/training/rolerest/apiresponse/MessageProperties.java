/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.rolerest.apiresponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * The MessageProperties class is represented as model class of the message.properties
 */

@Getter
@Component
public class MessageProperties {
	
	@Value( "${ERROR_CODE_200}")
	private String ERROR_CODE_200;
	
	@Value( "${ERROR_CODE_500}")
	private String ERROR_CODE_500;
	
	@Value( "${INERNAL_SERVER_ERROR}")
	private String INERNAL_SERVER_ERROR;
	
	@Value( "${SUCCESS}")
	private String SUCCESS;
	
	@Value( "${ROLE_ALREADY_EXISTS}")
	private String ROLE_ALREADY_EXISTS;
	
	@Value( "${MODULE_ALREADY_EXISTS}")
	private String MODULE_ALREADY_EXISTS;
	
	@Value( "${ROLE_MODULE_MAPPING_ALREADY_EXISTS}")
	private String ROLE_MODULE_MAPPING_ALREADY_EXISTS;
	
	@Value( "${NOT_FOUND}")
	private String NOT_FOUND;
}
