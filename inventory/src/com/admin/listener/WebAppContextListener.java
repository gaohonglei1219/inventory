package com.admin.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.admin.util.Const;
/**
 * 
* 类名称：WebAppContextListener.java
* 类描述： 在web容器启动时由WebAppContextListener初始化
* 作者：LRJ 
* 联系方式：
* @version 1.0
 */
public class WebAppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {



	}

	public void contextInitialized(ServletContextEvent event) {

		Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

	}

}
