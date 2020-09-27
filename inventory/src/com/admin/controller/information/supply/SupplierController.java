package com.admin.controller.information.supply;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.Page;
import com.admin.service.information.supply.SupplierService;
import com.admin.util.PageData;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304971134522581004L;
	@Autowired
	private SupplierService supplierService;
	
	/**
	 * 插入
	 * @return
	 */
	@RequestMapping("/insertSup")
	public ModelAndView insertSup(){
		PageData pd = this.getPageData();
		boolean res = supplierService.insertSup(pd);
		ModelAndView mv = new ModelAndView("redirect:querySupBylist");
		mv.addObject("res", res);
		return mv;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delSup")
	public ModelAndView delSup(){
		PageData pd = this.getPageData();
		boolean res = supplierService.delById(pd);
		ModelAndView mv = new ModelAndView("redirect:querySupBylist");
		mv.addObject("res", res);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@RequestMapping("/updateSup")
	public ModelAndView updateSup(){
		PageData pd = this.getPageData();
		boolean res = supplierService.updateSup(pd);
		ModelAndView mv = new ModelAndView("redirect:querySupBylist");
		mv.addObject("res", res);
		return mv;
	}
	/**
	 * 前往详情页面
	 * @return
	 */
	@RequestMapping("/goInfo")
	public ModelAndView goInfo(){
		PageData pd = this.getPageData();
		String method = pd.getString("method");
		ModelAndView mv = new ModelAndView("information/supplier/addsup");
		if("add".equals(method)){
			mv.addObject("getMethod", "insertSup");
			return mv;
		}
		PageData res = supplierService.queryData(pd);
		mv.addObject("getMethod", "updateSup");
		mv.addObject("pd", res);
		return mv;
	}
	/**
	 * 查询供应商列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/querySupBylist")
	public ModelAndView querySupByPage(Page page){
		PageData pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list = supplierService.querySup(page);
		ModelAndView mv = new ModelAndView("information/supplier/supplierList");
		mv.addObject("list", list);
		mv.addObject("pd",pd);
		return mv;
	}
	
	
}
