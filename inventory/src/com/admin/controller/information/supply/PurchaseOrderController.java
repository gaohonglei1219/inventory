package com.admin.controller.information.supply;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.Page;
import com.admin.service.information.supply.PurchaseOrderItemService;
import com.admin.service.information.supply.PurchaseOrderService;
import com.admin.service.information.supply.SingleItemService;
import com.admin.service.information.supply.SupplierService;
import com.admin.util.Const;
import com.admin.util.PageData;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8304971134522581005L;
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private PurchaseOrderItemService purchaseOrderItemService;
	
	/**
	 * 插入
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView insertSup(){
		PageData pd = this.getPageData();
		//添加其他字段信息
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		PageData pds = (PageData) session.getAttribute(Const.SESSION_userpds);
		pd.put("orderPurchaseEmployeeId",pds.get("USER_ID"));
		pd.put("orderItemCount", 0);
		pd.put("orderTotalNumber", 0);
		pd.put("orderTotalPrice", 0);
		boolean res = purchaseOrderService.insertSup(pd);
		ModelAndView mv = new ModelAndView("forward:/purchaseOrderItem/querylist");
		return mv;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delById")
	public ModelAndView delSup(){
		PageData pd = this.getPageData();
		boolean res = purchaseOrderService.delById(pd);
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
		String state = pd.getString("runState");
		if("2".equals(state)){
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			PageData pds = (PageData) session.getAttribute(Const.SESSION_userpds);
			pd.put("orderReceiveEmployeeId", pds.get("USER_ID"));
		}
		boolean res = purchaseOrderService.updateById(pd);
		ModelAndView mv = new ModelAndView("redirect:querylist");
		mv.addObject("runState", pd.get("runState"));
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
		ModelAndView mv = new ModelAndView();
		String aimUrl = null;
		String viewName = null;
		if("add".equals(method)){
			viewName = "orderdetail";
			aimUrl = "insert";
			mv.addObject("getMethod", "insert");
			return mv;
		}else if("send".equals(method)){
			viewName = "orderSend";
			aimUrl = "updateById";
			mv.addObject("pd", pd);
		}else if("update".equals(method)){
			viewName = "orderdetail";
			PageData res = purchaseOrderService.queryById(pd);
			mv.addObject("pd", res);
			aimUrl = "updateById";
		}
		mv.setViewName("information/purchaseorder/"+viewName);
		mv.addObject("getMethod", aimUrl);
		
		return mv;
	}
	@RequestMapping("/toManagelist")
	public ModelAndView toManagelist(Page page){
		PageData pd = this.getPageData();
		page.setPd(pd);
		ModelAndView mv = new ModelAndView("information/purchaseorder/ordermanage");
		//查询订单下对应的详情列表
		List<PageData> orderList = purchaseOrderItemService.queryListPage(page);
		pd.put("id",pd.get("orderId"));
		//查询订单的详情信息
		PageData orderDetail = purchaseOrderService.queryById(pd);
		//查询所有状态
		List<PageData> allState = purchaseOrderItemService.getAllState();
		mv.addObject("allState",allState);
		mv.addObject("orderList",orderList);
		mv.addObject("orderDetail",orderDetail);
		return mv;
	}
	/**
	 * 查询供应商列表
	 * @param page
	 * @return
	 */
	@RequestMapping("/querylist")
	public ModelAndView querySupByPage(Page page,HttpSession session){
		PageData pd = this.getPageData();
		String stateStr = pd.getString("runState");
		if(stateStr==null){
			stateStr = (String)session.getAttribute("runState");
			pd.put("runState", stateStr);
		}else{
			session.setAttribute("runState", stateStr);
		}
		pd.put("stateIn","("+stateStr+")");
		page.setPd(pd);
		List<PageData> list = purchaseOrderService.queryListPage(page);
		ModelAndView mv = new ModelAndView("information/purchaseorder/orderlist");
		String listKey = null;
		String pageType = null;
		//根据提交状态的不同,选择列表页面的不同标签页
		switch(stateStr){
			case "1": listKey = "list";pageType="build";
			break;
			case "2": listKey = "sendList";pageType="send";
			break;
			case "4,5": listKey = "manageList";pageType="manage";
		}
		mv.addObject(listKey, list);
		mv.addObject("pageType", pageType);
		mv.addObject("pd",pd);
		return mv;
	}
	
	@RequestMapping("/getAllFw")
	public @ResponseBody List<PageData> getAllFw(){
		return purchaseOrderService.getAllFw();
	}
	
	@RequestMapping("/getPayType")
	public @ResponseBody List<PageData> getPayType(){
		return purchaseOrderService.getPayType();
	}
	
	@RequestMapping("/getRunState")
	public @ResponseBody List<PageData> getRunState(){
		return purchaseOrderService.getRunState();
	}
	
	@RequestMapping("/getPayState")
	public @ResponseBody List<PageData> getPayState(){
		return purchaseOrderService.getPayState();
	}
	/**
	 * 新增订单单品完成,刷新数量
	 * @return
	 */
	@RequestMapping("/finshInsert")
	public ModelAndView finshInsert(){
		PageData pd = this.getPageData();
		Map<String,String> res = purchaseOrderItemService.getCountByOrder(pd);
		if(res!=null){
			pd.putAll(res);
			purchaseOrderService.updateCount(pd);
		}
		ModelAndView mv= new ModelAndView("redirect:querylist");
		return mv;
	}
	@RequestMapping("/saveManage")
	public @ResponseBody JSONObject saveManage(@RequestBody JSONObject json){
		//修改制定订单详情的状态
		for (Map.Entry<String,Object> entry : json.getJSONObject("state").entrySet()) {
			PageData pd = new PageData();
			pd.put("id", entry.getKey());
			pd.put("purchaseStateId", entry.getValue());
			purchaseOrderItemService.updateById(pd);
		}
		//修改订单的支付和流转状态
		PageData pd = new PageData();
		pd.put("runState", json.get("runState"));
		pd.put("orderPayStateId", json.get("payState"));
		pd.put("orderId", json.get("orderId"));
		purchaseOrderService.updateById(pd);
		return JSONObject.parseObject("{\"res\":\"success\"}");
		
	}
	
}
