package com.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2017年7月11日下午3:09:40
 * @author linkaitao
 * @Desc 模拟结果实体类
 */
public class MonitorBean implements Serializable {

	private static final long serialVersionUID = -4231400051111859399L;

	/**
	 * id
	 */
	private int morId;
	
	/**
	 * 类名
	 */
	private String className;
	
	/**
	 * 对象转化后的json字符串
	 */
	private String jsonString;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 创建人
	 */
	private String createBy;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 更新人
	 */
	private String updateBy;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 状态
	 */
	private String stsCd;

	public int getMorId() {
		return morId;
	}

	public void setMorId(int morId) {
		this.morId = morId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
