package com.wl.helper.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl.common.model.MyUser;
import com.wl.helper.server.HelperServer;


@Controller
@RequestMapping("/helperMain")
public class HelperMain {
	
	private static Logger logger = Logger.getLogger(HelperMain.class);
	HelperServer helperServer = new HelperServer();
	
	@RequestMapping("/visitHelper.ac")
	@ResponseBody
	public void visitHelper(HttpServletRequest request){
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")访问小助手");
	}
	
	@RequestMapping("/visitHistory.ac")
	@ResponseBody
	public void visitHistory(HttpServletRequest request){
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")访问历史记录");
	}
	
	@RequestMapping("/visitStringHelper.ac")
	@ResponseBody
	public void visitStringHelper(HttpServletRequest request){
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")访问字符处理");
	}
	
	@RequestMapping("/visitFileCompare.ac")
	@ResponseBody
	public void visitFileCompare(HttpServletRequest request){
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")访问简易文件对比");
	}
	
	@RequestMapping("/visitClearTemplateCache.ac")
	@ResponseBody
	public String visitClearTemplateCache(HttpServletRequest request){
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")访问清模板缓存");
		
		//String[] servers = {"10.86.8.17:8088","10.86.8.18:8088","10.86.8.19:8088","10.86.8.20:8088","10.86.8.21:8088","10.86.8.22:8088","10.86.8.23:8088","10.86.8.24:8088","10.86.8.28:8080/szcloud"};
		String[] servers = {"10.86.8.20:8088", "10.86.8.21:8088","10.86.8.22:8088","10.86.8.23:8088","10.86.8.24:8088","10.86.8.28:8080/szcloud"};
		String clearResult = helperServer.clearTemplateCache(servers);
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")清理模板缓存结果：" + clearResult);
		return clearResult;
	}
	
	@RequestMapping("/visitClearDBCache.ac")
	@ResponseBody
	public String visitClearDBCache(HttpServletRequest request){
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")访问清数据库缓存");
		
		String[] ips = {"10.86.1.11", "10.86.1.12"};
		int[] ports = {12000, 12000};
		String clearResult = helperServer.clearDBCache(ips, ports);
		logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")清理数据库缓存结果：" + clearResult);
		return clearResult;
	}

}
