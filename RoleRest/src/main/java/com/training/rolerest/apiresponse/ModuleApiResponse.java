package com.training.rolerest.apiresponse;

import java.util.List;

import com.training.rolerest.entityresponse.ModuleEntityApiResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The ModuleApiResponse class is used as the response class with the common response to be sent
 * related to Module management in the application.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ModuleApiResponse extends ApiReturnResponse {

	private static final long serialVersionUID = 1L;
	
	List<ModuleEntityApiResponse> moduleModelList;
}
