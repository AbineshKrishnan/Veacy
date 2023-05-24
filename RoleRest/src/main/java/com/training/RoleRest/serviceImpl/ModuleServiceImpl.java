package com.training.RoleRest.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.RoleRest.entity.Module;
import com.training.RoleRest.model.ModuleModel;
import com.training.RoleRest.repository.ModuleRepository;
import com.training.RoleRest.service.ModuleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Override
	public String createModule(ModuleModel moduleModel) {
		log.info("Entered into createModule method");
		try {
			Module module = new Module();
			module.setName(moduleModel.getModuleName());
			module.setDescription(moduleModel.getDescription());
			module.setDeleted(false);
			module.setActive(true);
			moduleRepository.save(module);
			log.debug("Saving Module{} data to the database ",module);
			return "Created Successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Module is not created");
			return "Error occured while Creating";
		}
	}

	@Override
	public List<Module> getAllModule() {
		log.debug("Fetching all the modules from the database");
		return (List<Module>) moduleRepository.findAll();
	}

	@Override
	public Optional<Module> getModuleByName(String moduleName) {
		log.debug("Fetching module with name {} from the database ", moduleName);
		Optional<Module> module = moduleRepository.findByName(moduleName);
		return module;
	}

	@Override
	public String updateModule(int id, ModuleModel moduleModel) {
		log.info("Entered into updateModule method");
		log.debug("Updating module with id {} with updates {} ",id ,moduleModel);
		try {
			Optional<Module> optionalRole = moduleRepository.findById(id);
			if(optionalRole.isPresent()) {
				Module module = optionalRole.get();
				module.setName(moduleModel.getModuleName());
				module.setDescription(moduleModel.getDescription());
				moduleRepository.save(module);
				log.debug("Updated module {} in the database ",module);
				return "Updated Successfully";
			}
			else {
				log.warn("Module with id {} not found in the database ",id);
				return "Invalid Request";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Module is not updated");
			return "Error occured while Updating";
		}
	}

	@Override
	public String deleteModule(int id) {
		log.info("Entered into updateModule method");
		log.debug("Deleting module with id {} from the database ", id);
		try {
			moduleRepository.deleteById(id);
			log.debug("Deleted the module with id {} from the database ",id);
			return "Deleted Successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Module is not deleted");
			return "Error occured while Deleting";
		}
	}
	
	@Override
	public Module update(String name, Map<String, Object> update) {
		log.info("Entered into updateModule method");
		log.debug("Updating module with name {} with updates {} ",name ,update);
		Optional<Module> oldModule = moduleRepository.findByName(name);
		if(oldModule.isPresent()) {
			Module newModule = oldModule.get();
			update.forEach((field, value) -> {
				switch (field) {
				case "name":
					newModule.setName((String) value);
					break;
				case "description":
					newModule.setDescription((String) value);
					break;
				case "isActive":
					newModule.setActive((Boolean) value);
					break;
				default:
					throw new IllegalArgumentException("Invalid field: " + field);
				}
			});
			log.debug("Updated module {} in the database", newModule);
			return moduleRepository.save(newModule);
		} else {
			log.error("Module with name {} not found in the database", name);
			throw new IllegalArgumentException("User with name " + name + " not found.");
		}
	}
}