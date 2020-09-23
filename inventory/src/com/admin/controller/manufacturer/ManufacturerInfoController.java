package com.admin.controller.manufacturer;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.entity.Brand;
import com.admin.entity.Manufacturer;
import com.admin.service.manufacturer.ManufacturerService;
import com.admin.util.Const;

/**
 * @Date:2017年10月18日上午9:11:11
 * @Author: linkaitao
 * @Desc: 品牌商信息维护控制层
 */
@Controller
@RequestMapping("manufacturerInfo")
public class ManufacturerInfoController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	/**
	 * @Date:2017年10月18日下午3:55:49
	 * @Author: linkaitao
	 * @Desc: 
	 */
	@RequestMapping("/showManufacturerinfo")
	public ModelAndView showManufacturerInfo(HttpSession session) {
		logger.debug("start showManufacturerInfo ...");
		ModelAndView modelAndView = new ModelAndView();
		Integer manBuyerId = (Integer)session.getAttribute(Const.MAN_BUYER_ID);
		logger.debug("session manBuyerId = " + manBuyerId);
		if (null != manBuyerId && 0 < manBuyerId) {
			Manufacturer manufacturerSelect = new Manufacturer();
			manufacturerSelect.setManId(manBuyerId);//根据manId查找品牌商
			manufacturerSelect.setStsCd("A");//状态为A表示有效
			Manufacturer manufacturer = manufacturerService.selectManufacturer(manufacturerSelect);
			if (null == manufacturer) {
				modelAndView.setViewName("manufacturerInfo/manufacturerInfoAdd");
			} else {
				//根据品牌信息获取关联的公司信息
				Brand brandSelect = new Brand();
				brandSelect.setManId(manBuyerId);
				brandSelect.setStsCd("A");//状态为A表示有效
				Brand brand = manufacturerService.selectBrand(brandSelect);
				logger.debug("brand info " + brand.getBrdId());
				modelAndView.addObject("manufacturer", manufacturer);//品牌商（公司）信息页面展示
				modelAndView.addObject("brand", brand);//品牌信息页面展示
				modelAndView.setViewName("manufacturerInfo/manufacturerInfoShow");
			}
		} else {//找不到用户的对应的品牌商Id，跳转到品牌商信息录入页面
			modelAndView.setViewName("manufacturerInfo/manufacturerInfoAdd");
		}
		return modelAndView;
	}
	
	
}
