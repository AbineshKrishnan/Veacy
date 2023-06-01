package com.training.rolerest.apiresponse;

import java.util.List;

import com.training.rolerest.entityresponse.RoleModuleMappingEntityApiResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The RoleModuleMappingApiResponse class is used as the response class with the common response to be sent
 * related to Role Module Mapping management in the application.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoleModuleMappingApiResponse extends ApiReturnResponse {

	private static final long serialVersionUID = 1L;
	
	List<RoleModuleMappingEntityApiResponse> roleModuleMappingEntityApiResponseList;
}
