package com.admin.service.manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.Brand;
import com.admin.entity.Manufacturer;
import com.admin.mapper.BrandMapper;
import com.admin.mapper.ManufacturerMapper;

/**
 * @Date:2017年10月18日上午10:17:58
 * @Author: linkaitao
 * @Desc: 品牌商信息service接口实现类
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerMapper manufacturerMapper;
	@Autowired
	private BrandMapper brandMapper;
	
	@Override
	public Manufacturer selectManufacturer(Manufacturer manufacturer) {
		return manufacturerMapper.selectManufacturer(manufacturer);
	}

	@Override
	public Brand selectBrand(Brand brand) {
		return brandMapper.selectBrand(brand);
	}

}
