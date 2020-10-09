package com.admin.controller.information.supply;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.Page;
import com.admin.service.information.supply.SupplierService;
import com.admin.service.information.supply.SupplierTypeService;
import com.admin.util.PageData;

@Controller
@RequestMapping("/supplierType")
public class SupplierTypeController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304971134522581005L;
	@Autowired
	private SupplierTypeService supplierTypeService;
	
	/**
	 * 插入
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView insertSup(){
		PageData pd = this.getPageData();
		boolean res = supplierTypeService.insert(pd);
		ModelAndView mv = new ModelAndView("redirect:querylist");
		mv.addObject("res", res);
		return mv;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delById")
	public ModelAndView delSup(){
		PageData pd = this.getPageData();
		boolean res = supplierTypeService.delById(pd);
		ModelAndView mv = new ModelAndView("redirect:querylist");
		mv.addObject("res", res);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@RequestMapping("/updateById")
	public ModelAndView updateSup(){
		PageData pd = this.getPageData();
		boolean res = supplierTypeService.updateById(pd);
		ModelAndView mv = new ModelAndView("redirect:querylist");
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
		ModelAndView mv = new ModelAndView("information/supplier/supplierTypeInfo");
		if("add".equals(method)){
			mv.addObject("getMethod", "insert");
			return mv;
		}
		PageData res = supplierTypeService.queryById(pd);
		mv.addObject("getMethod", "updateById");
		mv.addObject("pd", res);
		return mv;
	}
	/**
	 * 查询供应商列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/querylist")
	public ModelAndView querySupByPage(Page page){
		PageData pd = this.getPageData();
		page.setPd(pd);
		List<PageData> list = supplierTypeService.querylistPage(page);
		ModelAndView mv = new ModelAndView("information/supplier/supplierList");
		pd.put("pageType", "supType");
		mv.addObject("typeList", list);
		mv.addObject("pd",pd);
		mv.addObject("formIndex",1);
		return mv;
	}
	/**
	 * 拉取供应商列表信息json
	 */
	@RequestMapping("/queryAllType")
	public @ResponseBody List<Map<String,String>> queryAllType(){
		return supplierTypeService.queryAllType();
	}
	
	
}
