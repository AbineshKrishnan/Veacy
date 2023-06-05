/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.dto;

import java.util.List;

import com.kaytes.veacy.response.ModuleResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The ModuleApiResponse class is used as the response class with the common response to be sent
 * related to Module management in the application.
 */
@Getter
@Setter

public class ModuleApiResponse extends ApiReturnResponse {

	private static final long serialVersionUID = 1L;
	
	List<ModuleResponse> moduleModelList;
}
