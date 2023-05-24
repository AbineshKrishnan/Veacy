package com.training.RoleRest.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleModuleMappingModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int roleId;
	
	private int moduleId;
	
	private boolean createe;
	
	private boolean reead;
	
	private boolean updatee;
	
	private boolean deletee;
	
	private boolean isDeleted = false;
}
