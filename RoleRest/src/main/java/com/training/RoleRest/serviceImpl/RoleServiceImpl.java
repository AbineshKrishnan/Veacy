package com.training.RoleRest.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.training.RoleRest.entity.Role;
import com.training.RoleRest.model.RoleModel;
import com.training.RoleRest.repository.RoleRepository;
import com.training.RoleRest.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public String createRole(Role role) {
		log.info("Entered into createRole method");
        try {
        	roleRepository.save(role);
        	log.debug("Saving Role {} data to the database ",role);
        	return "Created Successfully";
        }
        catch(Exception e) {
        	log.warn("Role is not created");
        	return "Error occured while Creating";
        }
	}

	@Override
	public List<Role> getAllRole() {
		log.debug("Fetching all the roles from the database");
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> getRoleByName(String name) {
		log.debug("Fetching role with name {} from the database ", name);
		return roleRepository.findByName(name);
	}

	@Override
	public String updateRole(String name, RoleModel roleModel) {
		log.info("Entered into updateRole method");
		log.debug("Updating module with name {} with updates {} ",name ,roleModel);
		try {
			Optional<Role> optionalRole = roleRepository.findByName(name);
			if(optionalRole.isPresent()) {
				Role role = optionalRole.get();
				role.setName(roleModel.getName());
				role.setDescription(roleModel.getDescription());
				role.setInvitee(roleModel.isInvitee());
				roleRepository.save(role);
				log.debug("Updated module {} in the database ",role);
				return "Updated Successfully";
			}
			else {
				log.warn("Role with name {} not found in the database ",name);
				return "Invalid Request";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Role is not updated");
			return "Error occured while Updating";
		}
	}

	@Override
	public String deleteRole(int id) {
		log.info("Entered into deleteRole method");
		log.debug("Deleting role with id {} from the database ", id);
		try {
			roleRepository.deleteById(id);
			log.debug("Deleted the module with id {} from the database ",id);
			return "Deleted Successfully";
		}
		catch(Exception e) {
			e.printStackTrace();
			log.warn("Module is not deleted");
			return "Error while Deleting";
		}
	}

	@Override
	public Role update(String name, Map<String, Object> updates) {
		log.info("Entered into updateModule method");
		log.debug("Updating module with name {} with updates {} ",name ,updates);
        Optional<Role> existingRole = roleRepository.findByName(name);
        if (existingRole.isPresent()) {
        	Role updatedRole = existingRole.get();
            updates.forEach((field, value) -> {
                switch (field) {
                    case "name":
                    	updatedRole.setName((String) value);
                        break;
                    case "description":
                    	updatedRole.setDescription((String) value);
                        break;
                    case "isInvitee":
                    	updatedRole.setInvitee((Boolean) value);
                        break;
                    case "isActive":
                    	updatedRole.setActive((Boolean) value);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid field: " + field);
                }
            });
            Role savedUser = roleRepository.save(updatedRole);
            log.debug("Updated module {} in the database", savedUser);
            return savedUser;
        } else {
        	log.error("Module with name {} not found in the database", name);
            throw new IllegalArgumentException("User with name " + name + " not found.");
        }
    }
}
