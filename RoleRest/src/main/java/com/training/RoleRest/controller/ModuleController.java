package com.training.RoleRest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.training.RoleRest.entity.Module;
import com.training.RoleRest.model.ModuleModel;
import com.training.RoleRest.service.ModuleService;

@RestController
@RequestMapping("/module")
public class ModuleController implements ModuleControllerSwagger{
	
	@Autowired
	ModuleService moduleService;
	
	@PostMapping("/create")
	public String createModule(@RequestBody ModuleModel moduleModel) {
		String module = moduleService.createModule(moduleModel);
		return module;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Module>> getAllModule(){
		List<Module> module = moduleService.getAllModule();
		return new ResponseEntity<>(module, HttpStatus.OK);
	}
	
	@GetMapping("/get/{moduleName}")
	public ResponseEntity<Optional<Module>> getModuleByName(@PathVariable("moduleName") String ModuleName){
		Optional<Module> module = moduleService.getModuleByName(ModuleName);
		if(module !=null) {
			return ResponseEntity.ok(module);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/update/{id}")
	public String updateModule(@PathVariable int id,@RequestBody ModuleModel moduleModel){
		String module = moduleService.updateModule(id,moduleModel);
		if(module !=null) {
			return "Updated Successfully";
		}
		else {
			return "Error!";
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteModule(@PathVariable int id) {
		moduleService.deleteModule(id);
		return "Deleted Successfully";
	}
	
	@PatchMapping("/edit/{name}")
	public Module updateModule(@PathVariable String name, @RequestBody Map<String, Object> update) {
		return moduleService.update(name, update);
	}
}
