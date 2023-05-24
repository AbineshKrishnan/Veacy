package com.training.RoleRest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.training.RoleRest.entity.Module;
import com.training.RoleRest.model.ModuleModel;

public interface ModuleService {
	
	public String createModule(ModuleModel moduleModel);

	public List<Module> getAllModule();

	public Optional<Module> getModuleByName(String moduleName);

	public String updateModule(int id, ModuleModel moduleModel);

	public String deleteModule(int id);

	Module update(String name, Map<String, Object> update);
}
