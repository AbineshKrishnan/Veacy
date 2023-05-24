package com.training.RoleRest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.training.RoleRest.entity.Role;
import com.training.RoleRest.model.RoleModel;

public interface RoleService {
	
	public String createRole(Role role);

	public List<Role> getAllRole();

	public Optional<Role> getRoleByName(String name);

	public String updateRole(String name, RoleModel roleModel);

	public String deleteRole(int id);

	public Role update(String name, Map<String, Object> updates);
}
