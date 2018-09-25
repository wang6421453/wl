package com.wl.admin.service;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import com.wl.admin.action.TotalVisterCounter;



public class MyApplicationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		//设置代码目录和网站根目录
		ProjectPath.setProjectPathName(servletContextEvent.getServletContext().getRealPath("/"));  //D:\JavaCode\works\wl\Web\
		ProjectPath.setWebRoot(servletContextEvent.getServletContext().getContextPath());  // /wl
		//设置hibernate路径(貌似一定要放到class里才行)
		ProjectPath.setHibePathOnline(servletContextEvent.getServletContext().getInitParameter("hibePathOnline"));
		ProjectPath.setHibePathOffline(servletContextEvent.getServletContext().getInitParameter("hibePathOffline"));
		//初始化总访问量
		TotalVisterCounter.initTotalVisterNum();
		//初始化log4j
		System.setProperty("projectPath", servletContextEvent.getServletContext().getRealPath("/"));
		String log4jPro = servletContextEvent.getServletContext().getInitParameter("log4jProper");
		PropertyConfigurator.configure(servletContextEvent.getServletContext().getRealPath("/") + log4jPro);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//关闭时保存访问量数据
		TotalVisterCounter.saveDataWhenShutDown();
	}

}
