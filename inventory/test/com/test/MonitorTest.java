package com.test;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.admin.entity.MonitorBean;
import com.admin.entity.system.User;
import com.admin.service.monitor.MonitorService;
import com.admin.util.PageData;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/ApplicationContext.xml"})
public class MonitorTest {
	
	private static Logger logger = Logger.getLogger(MonitorTest.class);
	
	@Autowired
	private MonitorService monitorService;
	
	/*@Test
	public void testSaveMonitor() throws Exception {
		PageData pageData = new PageData();
		pageData.put("className", "user");
		pageData.put("jsonString", "{'updateBy':'lkt','morId':10086,'className':'com.admin.entity.MonitorBean','createBy':'lkt'}");
		pageData.put("stsCD", "A");
		monitorService.saveMonitor(pageData);
		logger.debug("pageData=" + JSON.toJSONString(pageData));
	}*/
	
	@Test
	public void testFindMonitorById() throws Exception {
		logger.info("-----info------------");
		logger.debug("--------debug-------------------");
		/*PageData pageData = new PageData();
		pageData.put("morId", "1");
		PageData pageDataRes = monitorService.findMonitorById(pageData);
		System.out.println(JSON.toJSON(pageDataRes));*/
		/*String className = pageDataRes.getString("CLASS_NAME");
		String jsonString = pageDataRes.getString("JSON_STRING");
		System.out.println("className=" + className + ",jsonString=" + jsonString);
		User user = (User)JSON.parseObject(jsonString, Class.forName(className));
		System.out.println("user" + user);*/
		MonitorBean monitorBean = monitorService.findMonitorById(1);
		logger.info("monitorBean=" + JSON.toJSON(monitorBean));
	}
	
	@Test
	public void testJsonParse()  throws Exception {
		System.out.println(User.class);
		System.out.println(Class.forName("com.admin.entity.system.User"));
		User user = new User();
		user.setUSER_ID("1");
		user.setNAME("test");
		user.setPASSWORD("123456");
		user.setSTATUS("A");
		System.out.println(JSON.toJSON(user));
		logger.debug("test");
		
		MonitorBean monitorBean = new MonitorBean();
		monitorBean.setMorId(10086);
		monitorBean.setClassName(MonitorBean.class.getName());
		monitorBean.setCreateBy("lkt");
		monitorBean.setUpdateBy("lkt");
		System.out.println(JSON.toJSON(monitorBean));
	}
	
	@Test
	public void testFindMonitorList() throws Exception {
		/*PageData pageData = new PageData();
		List<PageData> pageDataList = monitorService.findMonitorListWithPage(pageData);
		logger.debug(pageDataList);*/
		List<MonitorBean> monitorBeanList = monitorService.findMonitorList();
		logger.info("monitorBeanList=" + JSON.toJSON(monitorBeanList));
	}
	
	/**
	 * @Date 2017年7月17日上午11:01:36
	 * @author linkaitao
	 * @Desc 测试返回monitor对象
	 */
	/*@Test
	public void testFindMonitorByIdWidthReturnObject() throws Exception {
		logger.info("-----info------------");
		logger.debug("--------debug-------------------");
		PageData pageData = new PageData();
		pageData.put("morId", "1");
		MonitorBean monitorBean = monitorService.findMonitorByIdWidthReturnObject(pageData);
		System.out.println(JSON.toJSON(monitorBean));
	}*/
	
	/**
	 * @Date 2017年7月17日上午11:01:36
	 * @author linkaitao
	 * @Desc 测试返回monitor对象,入参也是对象
	 */
	@Test
	public void testFindMonitor() throws Exception {
		MonitorBean monitorReq = new MonitorBean();
		monitorReq.setMorId(1);
		monitorReq.setClassName("user");
		MonitorBean monitorBean = monitorService.findMonitor(monitorReq);
		System.out.println(JSON.toJSON(monitorBean));
	}
	
	/**
	 * @Date 2017年7月18日下午9:12:20
	 * @author linkaitao
	 * @Desc
	 */
	@Test
	public void testSaveMonitorBean() throws Exception {
		MonitorBean monitorBean = new MonitorBean();
		monitorBean.setClassName("com.admin.Monitor");
		monitorBean.setJsonString("test");
		monitorService.saveMonitorBean(monitorBean);
		logger.debug("monitorBean=" + JSON.toJSONString(monitorBean));
	}
	
	@Test
	public void testUpdateMonitorBean() throws Exception {
		MonitorBean monitorBean = new MonitorBean();
		monitorBean.setClassName("com.admin.Monitor");
		monitorBean.setJsonString("testUpdate");
		monitorBean.setMorId(1);
		monitorBean.setUpdateBy("system");
		monitorBean.setUpdateDate(new Date());
		monitorBean.setStsCd("A");
		monitorService.updateMonitor(monitorBean);
		
	}
	
	/**
	 * 拆单方法，调用前确保pro,ofp,man,str数据初始化完成
	 * @throws Exception
	 */
	//@Test
	public void testBreakOrder()  throws Exception {
		monitorService.updateMonitorBreakOrder();
	}

}
