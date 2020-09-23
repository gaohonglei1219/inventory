package com.admin.entity.system;

import java.io.Serializable;

public class RoleIdAndRights implements Serializable{
	private static final long serialVersionUID = 6109453732956321400L;
	
	private int roleId;
	private String[] rights;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String[] getRights() {
		return rights;
	}
	public void setRights(String[] rights) {
		this.rights = rights;
	}
	
	
}
