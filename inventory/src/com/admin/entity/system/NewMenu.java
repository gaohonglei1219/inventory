package com.admin.entity.system;

import java.io.Serializable;

/**
 * 类名NewMenu
 * 作者：张通
 * 时间：2017年7月18日
 */

public class NewMenu implements Serializable{
	private static final long serialVersionUID = -6961176818109242922L;
	private int menuId;
	private String menuName;
	private String menuUrl;
	private int parentId;
	private String menuOrder;
	private String menuIcon;
	private String menuType;
	private String createdBy;
	private String creationDate;
	private String lastUpdateBy;
	private String lastUpdateDate;
	private int callCnt;
	private String remark;
	private String stsCd;
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int ParentId) {
		this.parentId = ParentId;
	}
	public String getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
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
	public String getLastUpdateBy() {
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


