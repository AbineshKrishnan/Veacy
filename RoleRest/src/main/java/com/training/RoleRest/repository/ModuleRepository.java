package com.training.RoleRest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.RoleRest.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

	Optional<Module> findByName(String name);

}
