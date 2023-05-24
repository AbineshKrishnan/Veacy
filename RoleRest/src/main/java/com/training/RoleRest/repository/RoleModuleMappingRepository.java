package com.training.RoleRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.RoleRest.entity.RoleModuleMapping;

@Repository
public interface RoleModuleMappingRepository extends JpaRepository<RoleModuleMapping, Integer> {

	@Query(nativeQuery = true,value = "select * from role_module_mapping_table where role_id = ?")
	List<RoleModuleMapping> getByRoleId(int id);
	
	@Query(nativeQuery = true,value = "select * from role_module_mapping_table where module_id = ?")
	List<RoleModuleMapping> getByModuleId(int id);
}
