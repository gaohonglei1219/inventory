package com.admin.service.manufacturer;

import com.admin.entity.Brand;
import com.admin.entity.Manufacturer;

/**
 * @Date:2017年10月18日上午10:16:56
 * @Author: linkaitao
 * @Desc: 品牌商信息service接口类
 */
public interface ManufacturerService {
	
	/**
	 * @Date:2017年10月18日上午10:16:31
	 * @Author: linkaitao
	 * @Desc: 根据输入条件查找公司信息
	 */
	public Manufacturer selectManufacturer(Manufacturer manufacturer);
	
	/**
	 * @Date:2017年10月18日下午3:20:05
	 * @Author: linkaitao
	 * @Desc: 根据输入条件查找品牌信息
	 */
	public Brand selectBrand(Brand brand);
	
}
