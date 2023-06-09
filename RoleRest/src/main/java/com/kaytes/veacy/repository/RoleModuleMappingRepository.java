/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kaytes.veacy.entity.RoleModuleMapping;

@Repository
public interface RoleModuleMappingRepository extends JpaRepository<RoleModuleMapping, Long> {

	@Query(nativeQuery = true,value = "select * from role_module_mapping_table where role_id = ? and is_deleted = false")
	List<RoleModuleMapping> getByRoleId(Long id);
	
	@Query(nativeQuery = true,value = "select * from role_module_mapping_table where module_id = ? and is_deleted = false")
	List<RoleModuleMapping> getByModuleId(Long id);
	
	@Query(nativeQuery = true, value = "select * from role_module_mapping_table where module_id =? and role_id =? and is_deleted = false")
	Optional<RoleModuleMapping> getRoleIdAndModuleId(Long moduleId, Long roleId);
}
