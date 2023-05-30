package com.training.RoleRest.Response;

import java.util.List;

import com.training.RoleRest.response.RoleEntityApiResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoleApiResponse extends ApiReturnResponse {
	
	private static final long serialVersionUID = 1L;
	
	List<RoleEntityApiResponse> roleEntityApiResponseList;
}
