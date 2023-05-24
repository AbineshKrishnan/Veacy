package com.training.RoleRest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "module_table")
@SQLDelete(sql = "UPDATE module_table SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Getter
@Setter
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "module_name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_deleted")
	private boolean isDeleted = false;
	
	@Column(name = "is_active")
	private boolean isActive = true;
	
}