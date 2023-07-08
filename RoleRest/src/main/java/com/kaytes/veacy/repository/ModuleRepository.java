/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.kaytes.veacy.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.kaytes.veacy.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

	@Query(nativeQuery = true,value = "select * from module_table where module_name = ? and is_deleted = false")
	Optional<Module> findByModuleName(String moduleName);
}
