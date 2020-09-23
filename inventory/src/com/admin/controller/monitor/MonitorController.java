package com.admin.controller.monitor;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.MonitorBean;
import com.admin.service.monitor.MonitorService;
import com.admin.util.PageData;
import com.alibaba.fastjson.JSON;

/**
 * 
 * @Date 2017年7月11日下午1:40:47
 * @author linkaitao
 * @Desc 提供前端模拟调用的控制层
 */
@Controller
@RequestMapping(value="/monitor")
public class MonitorController extends BaseController {
	
	private static final long serialVersionUID = 8911571359327480546L;
	
	@Autowired
	private MonitorService monitorService;
	/**
	 * 
	 * @Date 2017年7月11日下午2:14:07
	 * @author linkaitao
	 * @Desc 打开添加页面
	 */
	@RequestMapping(value="/goAddMonitor")
	public ModelAndView goAddMonitor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("monitor/monitorForm");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Date 2017年7月11日下午2:14:07
	 * @author linkaitao
	 * @Desc 打开编辑页面
	 */
	@RequestMapping(value="/goEditMonitor")
	public ModelAndView goEditMonitor() {
		ModelAndView modelAndView = new ModelAndView();
		/**
		 * 待实现
		 */
		//修改冲突提交2
		return modelAndView;
	}
	
	/**
	 * 
	 * @Date 2017年7月11日下午2:14:07
	 * @author linkaitao
	 * @Desc 保存信息
	 */
	@RequestMapping(value="/saveMonitor", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	public ModelAndView saveMonitor(MonitorBean monitorBean) {
		ModelAndView modelAndView = new ModelAndView("redirect:showMonitorList");
		
		logger.debug("monitorBean=" + JSON.toJSONString(monitorBean));
		try {
			monitorBean.setStsCd("A");
			monitorBean.setCreateBy("system");
			monitorBean.setCreateDate(new Date());
			monitorService.saveMonitorBean(monitorBean);
			modelAndView.addObject("saveMsg", "保存成功");
			//modelAndView.setViewName("monitor/monitorList");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			logger.error("保存信息失败", e);
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * @Date 2017年7月11日下午2:14:07
	 * @author linkaitao
	 * @throws Exception 
	 * @Desc 查询模拟结果
	 */
	@RequestMapping(value="/findMonitor")
	@ResponseBody
	public String findMonitor() {
		String result = "";
		PageData pageData = this.getPageData();
		logger.debug("pageData=" + JSON.toJSONString(pageData));
		//logger.debug("monitorBeanReq=" + JSON.toJSONString(monitorBeanReq));
		try {
			/*PageData pageDataResp = monitorService.findMonitorById(pageData);
			String jsonString = (String) pageDataResp.get("JSON_STRING");*/ 
			MonitorBean monitorBean = new MonitorBean();
			monitorBean.setMorId(Integer.valueOf(pageData.getString("morId")));
			MonitorBean monitorResp = monitorService.findMonitor(monitorBean);
			String jsonString = monitorResp.getJsonString();
			result = jsonString;
			logger.debug("查询模拟结果:" + result);
		} catch (Exception e) {
			logger.error("查询模拟结果失败", e);
		}
		return result;
	}
	
	/**
	 * 
	 * @Date 2017年7月19日下午11:11:41
	 * @author linkaitao
	 * @Desc
	 */
	@RequestMapping(value="/findMonitorNew", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findMonitorNew(@RequestBody MonitorBean monitorBean) {
		logger.debug("monitorBean=" + JSON.toJSONString(monitorBean));
		
		String result = "测试中文";
		return result;
	}
	
	
	private MonitorBean getMonitorBeanReq() {
		MonitorBean monitorBeanReq = new MonitorBean();
		PageData pageData = this.getPageData();
		//monitorBeanReq.setMorId(morId);
		//测试提交
		return monitorBeanReq;
	}
	
	
	/**
	 * 
	 * @Date 2017年7月11日下午2:14:07
	 * @author linkaitao
	 * @throws Exception 
	 * @Desc 查询模拟结果
	 */
	@RequestMapping(value="/findMonitorView")
	@ResponseBody
	public ModelAndView findMonitorView() {
		ModelAndView modelAndView = new ModelAndView();
		PageData pageData = this.getPageData();
		String viewName = (String)pageData.get("viewName");
		logger.debug("viewName=" + viewName);
		if (StringUtils.isEmpty(viewName)) {
			viewName = "monitor/monitorList";
		}
		modelAndView.setViewName(viewName);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Date 2017年7月11日下午1:57:36
	 * @author linkaitao
	 * @Desc 模拟结果列表
	 */
	@RequestMapping(value="/showMonitorList", produces="text/html;charset=UTF-8")
	public ModelAndView showMonitorList() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			//List<PageData> pageDataList = monitorService.findMonitorListWithPage(pageData);
			List<MonitorBean> monitorBeanList = monitorService.findMonitorList();
			modelAndView.addObject("monitorBeanList", monitorBeanList);
			modelAndView.setViewName("monitor/monitorList");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			logger.error("查询模拟结果列表异常", e);
		}
		return modelAndView;
	}
	
	/**
	 * @Date:2017年10月18日上午8:52:25
	 * @Author: linkaitao
	 * @Desc: 模拟拉单、拆单接口
	 */
	@RequestMapping(value="/monitorBreakOrder", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String monitorBreakOrder() {
		logger.debug("模拟拆单");
		String result = "success";
		try {
			monitorService.updateMonitorBreakOrder();
		} catch (Exception e) {
			result = "false";
		}
		return result;
	}
	
	
}
