/*
 * Copyright (C) 2023-2024 Kaytes Pvt Ltd. The right to copy, distribute, modify, or otherwise
 * make use of this software may be licensed only pursuant to the terms of an applicable Kaytes Pvt Ltd license agreement.
 */
package com.training.RoleRest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role_module_mapping_table")
@SQLDelete(sql = "UPDATE role_module_mapping_table SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Getter
@Setter
public class RoleModuleMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role roleId;
	
	@OneToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id")
	private Module moduleId;
	
	@Column(name = "createe")
	private boolean createe;
	
	@Column(name = "reead")
	private boolean reead;
	
	@Column(name = "updatee")
	private boolean updatee;
	
	@Column(name = "deletee")
	private boolean deletee;
	
	@Column(name = "is_deleted")
	private boolean isDeleted = false;
}
