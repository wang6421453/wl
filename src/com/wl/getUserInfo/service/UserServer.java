package com.wl.getUserInfo.service;

import java.sql.SQLException;
import java.util.List;

import com.wl.getUserInfo.dao.UserDAO;
import com.wl.getUserInfo.model.UserVO;




public class UserServer {
    private UserDAO dao;
	
	public UserServer(int onlineOrOff) throws ClassNotFoundException, SQLException{
		dao = new UserDAO(onlineOrOff);
	}
	
	/**
	 * 获取用户列表
	 * @param userVO 用户VO
	 * @return List<UserVO> 用户VO list
	 */
	public List<UserVO> getUserList(UserVO userVO){
		return dao.getUserList(userVO);
	}
	
	/**
     * 获取用户数量
     * @param userVO 用户VO
     * @return int 用户数量
     */
	public int getUserCount(UserVO userVO){
		return dao.getUserCount(userVO);
	}
	
	/**
	 * 修改手机号
	 * @param userVO
	 * @return int 是否成功修改
	 */
	public int changePhoneNo(UserVO userVO){
		return dao.changePhoneNo(userVO);
	}
	
	/**
	 * 查询用户手机号
	 * @param userVO
	 * @return phoneNo
	 */
	public String getUserPhoneNo(UserVO userVO){
		return dao.getUserPhoneNo(userVO);
	}

}
