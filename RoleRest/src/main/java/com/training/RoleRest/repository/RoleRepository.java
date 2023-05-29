/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.RoleRest.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(nativeQuery = true,value = "select * from role where name = ? and is_deleted = false")
	Optional<Role> findByName(String name);

}
