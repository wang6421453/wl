package com.wl.szcloud.action;
/*
 * author 王磊
 * 获取用户信息Action
 * date 2015-4-23
 */
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

import com.wl.admin.service.OnLineCounterListener;
import com.wl.common.model.MyUser;
import com.wl.szcloud.model.SzcloudUserVO;
import com.wl.szcloud.service.SzcloudUserServer;


@Controller
@RequestMapping("/szcloud/userInfo")
public class GetSzcloudUserInfoAction{
	
	private static Logger logger = Logger.getLogger(OnLineCounterListener.class);
	private static Logger updatelogger = Logger.getLogger("updateLogger");
	
	@RequestMapping("/getUserInfo.ac")
	@ResponseBody
	public Map<String, Object> getUserInfo(@ModelAttribute SzcloudUserVO userVO, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String, Object>();
		String onlineOrOff = userVO.getOnlineOrOff();
		SzcloudUserServer userServer = null;
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		try {
			userServer = new SzcloudUserServer(Integer.parseInt(onlineOrOff));
			int count = userServer.getUserCount(userVO);
			List<SzcloudUserVO> lstUserVO = new ArrayList<SzcloudUserVO>();
			logger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")查询政务云" + ("0".equals(onlineOrOff)?"线上":"线下") + userVO.getUserName() + "|" + userVO.getCompName() + "|" + userVO.getCompCode() + "|" + userVO.getCardNo()+ "的信息");
			if(count != 0){
				lstUserVO = userServer.getUserList(userVO);
			}
			result.put("totalCount", count);
			result.put("lstUserVO", lstUserVO);
		} catch (Exception e) {
			logger.error(e);
		} finally{
			userServer.closeSession();
		}
		return result;
	}
	
	@RequestMapping("/changePhoneNo.ac")
	@ResponseBody
	public int changePhoneNo(@ModelAttribute SzcloudUserVO userVO, HttpServletRequest request){
		int result = 0;
		String onlineOrOff = userVO.getOnlineOrOff();
		SzcloudUserServer userServer = null;
		MyUser myUser = (MyUser) request.getSession().getAttribute("myUser");
		try {
			userServer = new SzcloudUserServer(Integer.parseInt(onlineOrOff));
			String oldPhoneNo = userServer.getUserPhoneNo(userVO);
			result = userServer.changePhoneNo(userVO);
			if(result != 0){
				updatelogger.info("用户:" + myUser.getRealName() + "(" +request.getRemoteAddr() +")修改政务云" + ("0".equals(onlineOrOff)?"线上":"线下") +userVO.getUserName()+ "的手机号" + oldPhoneNo + "为:" + userVO.getPhoneNo());
			}
		} catch (Exception e) {
			logger.error(e);
		} finally{
			userServer.closeSession();
		}
		return result;
	}

}
