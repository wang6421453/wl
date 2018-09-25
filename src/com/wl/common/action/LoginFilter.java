package com.wl.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wl.admin.service.ProjectPath;
import com.wl.common.model.MyUser;

public class LoginFilter implements Filter{
	
	private static Logger logger = Logger.getLogger(LoginFilter.class);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("LoginFilter 初始化");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String path = request.getRequestURI();   ///wl/szcloud/userInfo/getUserInfo.ac
		String contextPath = request.getContextPath(); ///wl
		String url = path.substring(contextPath.length()); ///szcloud/userInfo/getUserInfo.ac
		StringBuffer urll = request.getRequestURL();  //http://10.67.7.21/wl/szcloud/userInfo/getUserInfo.ac
		if((url.endsWith(".jsp") || url.endsWith(".ac")) && !"/login.jsp".equals(url) && !"/login.ac".equals(url) && !"/index.jsp".equals(url)){
			MyUser user = (MyUser)request.getSession().getAttribute("myUser");
			if(user == null){
				response.setHeader("sessionStatus", "timeout"); // 表示session timeout
				return;
				//response.sendRedirect(contextPath + "/login.jsp"); //异步方式跳转没作用
			}
		}
		
		chain.doFilter(request, response);  //有这句request就能继续传下去
	}

	@Override
	public void destroy() {
		logger.info("LoginFilter 结束");	
	}

}
