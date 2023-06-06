/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * The MessageProperties class is represented as model class of the message.properties
 */

@Getter
@Component
public class MessageProperties {
	
	@Value( "${errorCode200}")
	private int errorCode200;
	
	@Value( "${errorCode500}")
	private int errorCode500;
	
	@Value( "${errorCode400}")
	private int errorCode400;
	
	@Value( "${internalServerError}")
	private String internalServerError;
	
	@Value( "${success}")
	private String success;
	
	@Value( "${roleAlreadyExists}")
	private String roleAlreadyExists;
	
	@Value( "${moduleAlreadyExists}")
	private String moduleAlreadyExists;
	
	@Value( "${roleModuleMappingAlreadyExists}")
	private String roleModuleMappingAlreadyExists;
	
	@Value( "${notFound}")
	private String notFound;
	
	@Value( "${invalidInput}")
	private String invalidInput;
	
	@Value( "${invalidAttribute}")
	private String invalidAttribute;
}
