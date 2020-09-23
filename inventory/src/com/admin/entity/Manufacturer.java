package com.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date:2017年10月18日下午3:02:36
 * @Author: linkaitao
 * @Desc: 公司信息实体
 */
public class Manufacturer implements Serializable {

	private static final long serialVersionUID = -8730717064521265841L;
	
	private Integer manId;
	
	private String nameEn;
	
	private String nameCn;
	
	private String gmcReportType;
	
	private String gmcReportUrl;
	
	private String description;
	
	private String createdBy;
	
	private Date creationDate;
	
	private String lastUpdateBy;
	
	private Date lastUpdateDate;
	
	private Integer callCnt;
	
	private String remark;
	
	private String stsCd;

	public Integer getManId() {
		return manId;
	}

	public void setManId(Integer manId) {
		this.manId = manId;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getGmcReportType() {
		return gmcReportType;
	}

	public void setGmcReportType(String gmcReportType) {
		this.gmcReportType = gmcReportType;
	}

	public String getGmcReportUrl() {
		return gmcReportUrl;
	}

	public void setGmcReportUrl(String gmcReportUrl) {
		this.gmcReportUrl = gmcReportUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getCallCnt() {
		return callCnt;
	}

	public void setCallCnt(Integer callCnt) {
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
