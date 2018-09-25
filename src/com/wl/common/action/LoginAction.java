package com.wl.common.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl.common.model.MyUser;
import com.wl.common.utils.LoadMyUserInfo;

/*
 * author 王磊
 * 登陆Action
 * date:2015-11-19
 */


@Controller
@RequestMapping("/")
public class LoginAction {

	private static Logger logger = Logger.getLogger(LoginAction.class);
	@RequestMapping("/login.ac")
	@ResponseBody
	public Map<String, Object> login(@ModelAttribute MyUser myUser, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String userName = myUser.getUserName();
		String password = myUser.getPassword();
		String ip = request.getRemoteAddr();
		myUser.setIp(ip);
		logger.info("用户：" + userName + "(" + ip +")正在登陆...");
		List<MyUser> lstMyUser = LoadMyUserInfo.getMyUserList();
		for(MyUser user : lstMyUser){
			if(user.getUserName().equals(userName) && user.getPassword().equals(password) && (user.getIp().equals(ip) || user.getUserName().equals("wl") || user.getUserName().equals("zyc"))){
				request.getSession().setAttribute("myUser", user);
				result.put("status", "success");
				logger.info("用户：" + userName + "(" + ip +")登陆成功。");
				break;
			}
		}
		return result;
	}
	
	@RequestMapping("/logout.ac")
	@ResponseBody
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		logger.info("用户：" + myUser.getUserName() + "(" + myUser.getIp() +")主动退出系统...");
		request.getSession().removeAttribute("myUser");
		response.sendRedirect("login.jsp");
		return;
	}
}
