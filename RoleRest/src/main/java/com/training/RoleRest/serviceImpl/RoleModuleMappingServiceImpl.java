package com.training.RoleRest.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.RoleRest.entity.Module;
import com.training.RoleRest.entity.Role;
import com.training.RoleRest.entity.RoleModuleMapping;
import com.training.RoleRest.model.RoleModuleMappingModel;
import com.training.RoleRest.repository.RoleModuleMappingRepository;
import com.training.RoleRest.service.RoleModuleMappingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleModuleMappingServiceImpl implements RoleModuleMappingService {

	@Autowired
	RoleModuleMappingRepository roleModuleMappingRepository;
	
	@Override
	public String createRoleMapping(RoleModuleMapping roleModuleMapping) {
		log.info("Entered into createRoleMapping method");
		try {
			roleModuleMappingRepository.save(roleModuleMapping);
			log.debug("Saving RoleModuleMapping {} data to the database ",roleModuleMapping);
			return "Created Successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role is not created");
			return "Error occured while Creating";
		}
	}

	@Override
	public List<RoleModuleMapping> getAllRoleMappings() {
		log.debug("Fetching all the Role Module mappings from the database");
		return roleModuleMappingRepository.findAll();
	}

	@Override
	public String updateRoleMapping(int id, RoleModuleMappingModel roleModuleMappingModel) {
		log.info("Entered into updateRoleMapping method");
		log.debug("Updating Role Module Mapping with id {} with updates {} ",id ,roleModuleMappingModel);
		try {
			Optional<RoleModuleMapping> optionalRole = roleModuleMappingRepository.findById(id);
			if(optionalRole.isPresent()) {
				RoleModuleMapping roleModuleMapping = optionalRole.get();
//				roleModuleMapping.setRoleId(roleModuleMappingModel.getRoleId());
//				roleModuleMapping.setModuleId(roleModuleMappingModel.getModuleId());
				roleModuleMapping.setCreatee(roleModuleMappingModel.isCreatee());
				roleModuleMapping.setReead(roleModuleMappingModel.isReead());
				roleModuleMapping.setUpdatee(roleModuleMappingModel.isUpdatee());
				roleModuleMapping.setDeletee(roleModuleMappingModel.isDeletee());
				roleModuleMappingRepository.save(roleModuleMapping);
				log.debug("Updated Role Module Mapping {} in the database ",roleModuleMapping);
				return "Updated Successfully";
			}
			else {
				log.warn("Role Module Mapping with id {} not found in the database ",id);
				return "Invalid Request";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role Module Mapping is not updated");
			return "Error Occured while Updating";
		}
	}

	@Override
	public String deleteRoleMapping(int id) {
		log.info("Entered into deleteRoleMapping method");
		log.debug("Deleting Role Module Mapping with id {} from the database ", id);
		try {
			roleModuleMappingRepository.deleteById(id);
			log.debug("Deleted the Role Module Mapping with id {} from the database ",id);
			return "Deleted Successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role Module Mapping is not deleted");
			return "Error Occured while Deleting";
		}
	}

	@Override
	public List<RoleModuleMapping> getByRoleId(int id) {
		log.debug("Fetching the Role Module mappings with Role id {} from the database", id);
		return roleModuleMappingRepository.getByRoleId(id);
	}

	@Override
	public List<RoleModuleMapping> getBymoduleId(int id) {
		log.debug("Fetching all the Role Module mappings with Module id {} from the database",id);
		return roleModuleMappingRepository.getByModuleId(id);
	}

	@Override
	public RoleModuleMapping update(int id, Map<String, Object> update) {
		log.info("Entered into update method");
		log.debug("Updating Role Module Mapping with id {} with updates {} ",id ,update);
		Optional<RoleModuleMapping> oldModule = roleModuleMappingRepository.findById(id);
		if(oldModule.isPresent()) {
			RoleModuleMapping newModule = oldModule.get();	
			update.forEach((field, value) -> {
				switch (field) {
				case "roleId":
					Role role = new Role();
					role.setId((int) value);
					newModule.setRoleId(role);
					break;
				case "moduleId":
					Module module = new Module();
					module.setId((int) value);
					newModule.setModuleId(module);
					break;
				case "createe":
					newModule.setCreatee((Boolean) value);
					break;
				case "reead":
					newModule.setReead((Boolean) value);
					break;
				case "updatee":
					newModule.setUpdatee((Boolean) value);
					break;
				case "deletee":
					newModule.setDeletee((Boolean) value);
					break;
				default:
					throw new IllegalArgumentException("Invalid field: " + field);
				}
			});
			log.debug("Updated Role Module Mapping {} in the database", newModule);
			return roleModuleMappingRepository.save(newModule);
		} else {
			log.error("Module with id {} not found in the database", id);
			throw new IllegalArgumentException("User with name " + id + " not found.");
		}
	}
	
	
}
