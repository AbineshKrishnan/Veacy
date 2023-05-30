package com.training.RoleRest.Response;

import java.util.List;

import com.training.RoleRest.dto.RoleModuleMappingDto;
import com.training.RoleRest.model.RoleModuleMappingModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoleModuleMappingApiResponse extends ApiReturnResponse {

	private static final long serialVersionUID = 1L;
	
	List<RoleModuleMappingModel> roleModuleMappingModelList;
}
