package com.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date:2017年10月18日下午2:58:10
 * @Author: linkaitao
 * @Desc: 品牌信息实体
 */
public class Brand implements Serializable {

	private static final long serialVersionUID = 6680669923311291258L;
	
	private Integer brdId;
	
	private Integer manId;
	
	private String nameEn;
	
	private String nameCn;
	
	private String createdBy;
	
	private Date creationDate;
	
	private String lastUpdateBy;
	
	private Date lastUpdateDate;
	
	private Integer callCnt;
	
	private String remark;
	
	private String stsCd;

	public Integer getBrdId() {
		return brdId;
	}

	public void setBrdId(Integer brdId) {
		this.brdId = brdId;
	}

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
