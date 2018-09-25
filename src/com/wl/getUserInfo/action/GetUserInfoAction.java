package com.wl.getUserInfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wl.common.model.MyUser;
import com.wl.getUserInfo.model.UserVO;
import com.wl.getUserInfo.service.UserServer;
/*
 * author 王磊
 * 获取用户信息Action
 */


@Controller
@RequestMapping("/userInfo")
public class GetUserInfoAction{
	private static Logger logger = Logger.getLogger(GetUserInfoAction.class);
	private static Logger updatelogger = Logger.getLogger("updateLogger");
	@RequestMapping("/getUserInfo.ac")
	@ResponseBody
	public Map<String, Object> getUserInfo(@ModelAttribute UserVO userVO, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String, Object>();
		String onlineOrOff = userVO.getOnlineOrOff();
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		try {
			UserServer userServer = new UserServer(Integer.parseInt(onlineOrOff));
			int count = userServer.getUserCount(userVO);
			List<UserVO> lstUserVO = new ArrayList<UserVO>();
			logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")查询老平台" + ("0".equals(onlineOrOff)?"线上":"线下") + userVO.getUserName() + "|" + userVO.getCompName() + "|" + userVO.getCompCode() + "|" + userVO.getCardNo()+ "的信息");
			if(count != 0){
				lstUserVO = userServer.getUserList(userVO);
			}
			result.put("totalCount", count);
			result.put("lstUserVO", lstUserVO);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	@RequestMapping("/changePhoneNo.ac")
	@ResponseBody
	public int changePhoneNo(@ModelAttribute UserVO userVO, HttpServletRequest request){
		int result = 0;
		String onlineOrOff = userVO.getOnlineOrOff();
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		try {
			UserServer userServer = new UserServer(Integer.parseInt(onlineOrOff));
			String oldPhoneNo = userServer.getUserPhoneNo(userVO);
			result = userServer.changePhoneNo(userVO);
			if(result != 0){
				updatelogger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")修改老平台" + ("0".equals(onlineOrOff)?"线上":"线下") + userVO.getUserName()+ "的手机号"+ oldPhoneNo +"为:" + userVO.getPhoneNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

}
