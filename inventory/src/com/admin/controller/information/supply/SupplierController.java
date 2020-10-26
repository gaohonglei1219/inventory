package com.admin.controller.information.supply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.Page;
import com.admin.service.information.supply.SupplierService;
import com.admin.util.PageData;
import com.alibaba.fastjson.JSONObject;

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
	 * json插入
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/jsonInsert")
	public @ResponseBody JSONObject jsonInsert(@RequestBody Map<String,String> paramMap){
		PageData pd = new PageData();
		pd.putAll(paramMap);
		boolean res = supplierService.insertSup(pd);
		String tip = res?"成功":"失败";
		JSONObject json = JSONObject.parseObject("{\"result\":\"插入"+tip+"\",\"供应商名\":\""+pd.getString("name")+"\"}");
		return json;
	}
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delById")
	public ModelAndView delSup(){
		PageData pd = this.getPageData();
		boolean res = supplierService.delById(pd);
		ModelAndView mv = new ModelAndView("redirect:querySupBylist");
		mv.addObject("res", res);
		return mv;
	}
	
	/**
	 * json删除
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/jsonDel")
	public @ResponseBody JSONObject jsonDel(@RequestBody Map<String,String> paramMap){
		PageData pd = new PageData();
		pd.putAll(paramMap);
		boolean res = supplierService.delById(pd);
		String tip = res?"成功":"失败";
		JSONObject json = JSONObject.parseObject("{\"result\":\"删除"+tip+"\",\"供应商名\":\""+pd.getString("name")+"\"}");
		return json;
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
	 * json修改
	 * @param paramMap
	 * @return
	 */
	@RequestMapping("/jsonUpdate")
	public @ResponseBody JSONObject jsonUpdate(@RequestBody Map<String,String> paramMap){
		PageData pd = new PageData();
		pd.putAll(paramMap);
		boolean res = supplierService.updateSup(pd);
		String tip = res?"成功":"失败";
		JSONObject json = JSONObject.parseObject("{\"result\":\"修改"+tip+"\",\"供应商id\":\""+pd.getString("id")+"\"}");
		return json;
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
		mv.addObject("formIndex",0);
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * json接口分页查询
	 * @param paramMap
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAllSup")
	public @ResponseBody List<PageData> getAllSales(@RequestBody Map<String,String> paramMap,int currentPage) throws Exception{
		PageData pd = new PageData();
		pd.putAll(paramMap);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPd(pd);
		List<PageData>	varList = supplierService.querySup(page);
		return varList;
	}
	
	@RequestMapping("/getAllSupIdName")
	public @ResponseBody List<Map<String,String>> getAllSupIdName(){
		List<Map<String,String>> list = supplierService.getAllSupIdName();
		return list;
	}
}
