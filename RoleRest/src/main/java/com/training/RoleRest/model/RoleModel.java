package com.training.RoleRest.model;

public class RoleModel {
	 private int id;
	 private String name;
	 private String description;
	 private boolean isInvitee;
	 private boolean isDeleted;
	 private boolean isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isInvitee() {
		return isInvitee;
	}
	public void setInvitee(boolean isInvitee) {
		this.isInvitee = isInvitee;
	}
	public boolean  isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	 
}
