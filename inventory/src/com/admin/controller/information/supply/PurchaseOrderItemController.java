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
import com.admin.service.information.supply.PurchaseOrderItemService;
import com.admin.service.information.supply.SingleItemService;
import com.admin.service.information.supply.SupplierService;
import com.admin.util.PageData;

@Controller
@RequestMapping("/purchaseOrderItem")
public class PurchaseOrderItemController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304971134522581005L;
	@Autowired
	private PurchaseOrderItemService purchaseOrderItemService;
	
	/**
	 * 插入
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView insertSup(){
		PageData pd = this.getPageData();
		boolean res = purchaseOrderItemService.insertSup(pd);
		ModelAndView mv = new ModelAndView("redirect:querylist");
		mv.addObject("orderId", pd.get("orderId"));
		return mv;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delById")
	public ModelAndView delSup(){
		PageData pd = this.getPageData();
		boolean res = purchaseOrderItemService.delById(pd);
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
		boolean res = purchaseOrderItemService.updateById(pd);
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
		ModelAndView mv = new ModelAndView("information/purchaseorder/orderitemdetail");
		if("add".equals(method)){
			mv.addObject("getMethod", "insert");
			mv.addObject("pd",pd);
			return mv;
		}
		PageData res = purchaseOrderItemService.queryById(pd);
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
		List<PageData> list = purchaseOrderItemService.queryListPage(page);
		ModelAndView mv = new ModelAndView("information/purchaseorder/orderItemList");
		mv.addObject("list", list);
		mv.addObject("pd",pd);
		return mv;
	}
	
	@RequestMapping("/getAllState")
	public @ResponseBody List<PageData> getAllState(){
		return purchaseOrderItemService.getAllState();
		
	}
}
