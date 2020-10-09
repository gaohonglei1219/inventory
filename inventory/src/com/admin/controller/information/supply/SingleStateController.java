package com.admin.controller.information.supply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.Page;
import com.admin.service.information.supply.SingleItemService;
import com.admin.service.information.supply.SingleStateService;
import com.admin.service.information.supply.SingleTypeService;
import com.admin.service.information.supply.SupplierService;
import com.admin.util.PageData;
/**
 * 单品类型管理
 * @author csy
 *
 */
@Controller
@RequestMapping("/singleState")
public class SingleStateController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304971134522581007L;
	@Autowired
	private SingleStateService singleStateService;
	
	/**
	 * 插入
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView insertSup(){
		PageData pd = this.getPageData();
		boolean res = singleStateService.insertSup(pd);
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
		boolean res = singleStateService.delById(pd);
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
		boolean res = singleStateService.updateById(pd);
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
		ModelAndView mv = new ModelAndView("information/singleitem/singleState");
		if("add".equals(method)){
			mv.addObject("getMethod", "insert");
			return mv;
		}
		PageData res = singleStateService.queryData(pd);
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
		List<PageData> list = singleStateService.querySup(page);
		ModelAndView mv = new ModelAndView("information/singleitem/singlemain");
		mv.addObject("pageType","sinState");
		mv.addObject("stateList", list);
		mv.addObject("pd",pd);
		return mv;
	}
	
	@RequestMapping("/getAllIdName")
	public @ResponseBody List<Map<String,String>> getAllSupIdName(){
		List<Map<String,String>> list = singleStateService.getAllIdName();
		return list;
	}
}
