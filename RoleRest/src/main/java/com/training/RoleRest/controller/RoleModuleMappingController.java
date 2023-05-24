package com.training.RoleRest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.RoleRest.entity.RoleModuleMapping;
import com.training.RoleRest.model.RoleModuleMappingModel;
import com.training.RoleRest.service.RoleModuleMappingService;

@RestController
@RequestMapping("/rolemodulemapping")
public class RoleModuleMappingController implements RoleModuleMappingControllerSwagger{
	
	@Autowired
	RoleModuleMappingService roleMappingService;
	
	@PostMapping("/create")
	public String createRoleMapping(@RequestBody RoleModuleMapping roleModuleMapping){
		return roleMappingService.createRoleMapping(roleModuleMapping);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<RoleModuleMapping>> getAllRoleMappings(){
		List<RoleModuleMapping> roleModuleMappings = roleMappingService.getAllRoleMappings();
		return new ResponseEntity<>(roleModuleMappings, HttpStatus.OK);
	}
	
	@GetMapping("/getbyroleid/{id}")
	public ResponseEntity<List<RoleModuleMapping>> getByRoleId(@PathVariable int id){
		List<RoleModuleMapping> roleModuleMapping = roleMappingService.getByRoleId(id);
		return new ResponseEntity<>(roleModuleMapping, HttpStatus.OK);
	}
	
	@GetMapping("/getbymoduleid/{id}")
	public ResponseEntity<List<RoleModuleMapping>> getBymoduleId(@PathVariable int id){
		List<RoleModuleMapping> roleModuleMapping = roleMappingService.getBymoduleId(id);
		return new ResponseEntity<>(roleModuleMapping, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public String updateRoleMapping(@PathVariable int id, @RequestBody RoleModuleMappingModel roleModuleMappingModel){
		String response = roleMappingService.updateRoleMapping(id, roleModuleMappingModel);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteRoleMapping(@PathVariable int id) {
		roleMappingService.deleteRoleMapping(id);
		return "Deleted Successfully";
	}
	
	@PatchMapping("/edit/{id}")
	public RoleModuleMapping update(@PathVariable int id, @RequestBody Map<String, Object> update) {
		return roleMappingService.update(id, update);
	}
}
