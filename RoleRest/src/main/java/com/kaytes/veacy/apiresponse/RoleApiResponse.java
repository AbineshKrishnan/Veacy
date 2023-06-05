package com.kaytes.veacy.apiresponse;

import java.util.List;

import com.kaytes.veacy.entityresponse.RoleEntityApiResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The RoleApiResponse class is used as the response class with the common response to be sent
 * related to Role management in the application.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoleApiResponse extends ApiReturnResponse {
	
	private static final long serialVersionUID = 1L;
	
	List<RoleEntityApiResponse> roleModelList;
}
