package com.admin.entity.system;

import java.io.Serializable;

public class NewRole implements Serializable{
	private static final long serialVersionUID = -7714996157010004376L;
	private int roleId;
	private String roleName;
	private String visibleMenuId;
	private int parentId;
	private String createdBy;
	private String creationDate;
	private String lastUpdateBy;
	private String lastUpdateDate;
	private int callCnt;
	private String remark;
	private String stsCd;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getVisibleMenuId() {
		return visibleMenuId;
	}
	public void setVisibleMenuId(String visibleMenuId) {
		this.visibleMenuId = visibleMenuId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastUpdateB() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public int getCallCnt() {
		return callCnt;
	}
	public void setCallCnt(int callCnt) {
		this.callCnt = callCnt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStsCd() {
		return stsCd;
	}
	public void setStsCd(String stsCd) {
		this.stsCd = stsCd;
	}
}
