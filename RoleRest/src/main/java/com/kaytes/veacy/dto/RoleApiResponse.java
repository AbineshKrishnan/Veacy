/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.dto;

import java.util.List;

import com.kaytes.veacy.dto.response.RoleResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The RoleApiResponse class is used as the response class with the common response to be sent
 * related to Role management in the application.
 */

@Getter
@Setter

public class RoleApiResponse extends ApiReturnResponse {
	
	private static final long serialVersionUID = 1L;
	
	List<RoleResponse> roleModelList;
}
