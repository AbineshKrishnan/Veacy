package com.training.RoleRest.service;

import java.util.List;
import java.util.Map;

import com.training.RoleRest.entity.RoleModuleMapping;
import com.training.RoleRest.model.RoleModuleMappingModel;


public interface RoleModuleMappingService {

	String createRoleMapping(RoleModuleMapping roleModuleMapping);

	List<RoleModuleMapping> getAllRoleMappings();

	String updateRoleMapping(int id, RoleModuleMappingModel roleModuleMappingModel);

	String deleteRoleMapping(int id);

	List<RoleModuleMapping> getByRoleId(int id);

	List<RoleModuleMapping> getBymoduleId(int id);

	RoleModuleMapping update(int id, Map<String, Object> update);

}
