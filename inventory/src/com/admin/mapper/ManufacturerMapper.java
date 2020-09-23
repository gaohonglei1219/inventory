package com.admin.mapper;

import com.admin.entity.Manufacturer;

public interface ManufacturerMapper {
	
	/**
	 * @Date:2017年10月18日上午9:41:21
	 * @Author: linkaitao
	 * @Desc: 根据传入条件获取公司基本信息
	 */
	public Manufacturer selectManufacturer(Manufacturer manufacturer);

}
